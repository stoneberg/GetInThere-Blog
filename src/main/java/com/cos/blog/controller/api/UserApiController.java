package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.UserReq.UserDto;
import com.cos.blog.dto.UserReq.UserUpdateDto;
import com.cos.blog.handler.CommonAppException;
import com.cos.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody UserDto userDto) throws CommonAppException { // username, password, email
        log.info("UserApiController : save 호출됨");
        return new ResponseDto<>(HttpStatus.CREATED, userService.joinMember(userDto)); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<?> update(@RequestBody UserUpdateDto userUpdateDto) throws CommonAppException { // key=value, x-www-form-urlencoded
        userService.updateMember(userUpdateDto);
        // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
        // 세션 등록

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userUpdateDto.getUsername(), userUpdateDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<>(HttpStatus.OK);
    }

}
