package com.cos.blog.common.format;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cos.blog.handler.CommonAppException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Component
public class OauthLoginUtil {

    @SuppressWarnings("unchecked")
    public <T> T readKaKaoValue(ResponseEntity<String> response, Class<T> valueType) throws CommonAppException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Object kakaoValue = null;
        try {
            kakaoValue = objectMapper.readValue(response.getBody(), valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonAppException("카카오 로그인 중 오류가 발생했습니다.");
        }
        return (T) kakaoValue;
    }

}
