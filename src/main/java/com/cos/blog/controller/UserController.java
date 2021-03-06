package com.cos.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.common.format.OauthLoginUtil;
import com.cos.blog.dto.UserReq.UserDto;
import com.cos.blog.handler.CommonAppException;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static이하에 있는 /js/**, /css/**, /image/**

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @Value("${cos.key}")
    private String cosKey;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final OauthLoginUtil oauthLoginUtil;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    /**
     * 1.Authentication_Code로 Access_Token 요청 2.Access_Token으로 User_Profile 요청
     * 
     * @param code
     * @return
     * @throws CommonAppException
     */
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws CommonAppException { // Data를 리턴해주는 컨트롤러 함수

        // POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
        // Retrofit2
        // OkHttp
        // RestTemplate

        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "b344701c3ff69917f13cd47bb45df871");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> oauthResponse = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
                kakaoTokenRequest, String.class);

        // Gson, Json Simple, ObjectMapper
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        OAuthToken oauthToken = null;
//        try {
//            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        log.info("===============================================================================");
        OAuthToken oauthToken = oauthLoginUtil.readKaKaoValue(oauthResponse, OAuthToken.class);
        log.info("카카오 엑세스 토큰 : " + oauthToken.getAccessToken());
        log.info("===============================================================================");

        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccessToken());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> profileResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                kakaoProfileRequest2, String.class);
        log.info(profileResponse.getBody());

//        ObjectMapper objectMapper2 = new ObjectMapper();
//        objectMapper2.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        KakaoProfile kakaoProfile = null;
//        try {
//            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        log.info("===============================================================================");
        KakaoProfile kakaoProfile = oauthLoginUtil.readKaKaoValue(profileResponse, KakaoProfile.class);
        log.info("카카오 엑세스 토큰 : " + kakaoProfile.getKakaoAccount());
        log.info("===============================================================================");

        // User 오브젝트 : username, password, email
        log.info("카카오 아이디(번호) : " + kakaoProfile.getId());
        log.info("카카오 이메일 : " + kakaoProfile.getKakaoAccount().getEmail());

        log.info("블로그서버 유저네임 : " + kakaoProfile.getKakaoAccount().getEmail() + "_" + kakaoProfile.getId());
        log.info("블로그서버 이메일 : " + kakaoProfile.getKakaoAccount().getEmail());
        // UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
        log.info("블로그서버 패스워드 : " + cosKey);
        String kakaoUsername = StringUtils.substringBefore(kakaoProfile.getKakaoAccount().getEmail(), "@");
        UserDto kakaoUser = UserDto.builder().username(kakaoUsername)
                .password(cosKey).email(kakaoProfile.getKakaoAccount().getEmail()).oauth("kakao").build();

        // 가입자 혹은 비가입자 체크 해서 처리
        User originUser = userService.findMember(kakaoUser.getUsername());

        if (originUser.getUsername() == null) {
            log.info("기존 회원이 아니기에 자동 회원가입을 진행합니다");
            userService.joinMember(kakaoUser);
        }

        log.info("자동 로그인을 진행합니다.");
        // 로그인 처리
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
