package com.cos.blog.dto;

import com.cos.blog.model.User;

import lombok.Builder;
import lombok.Data;

public class UserReq {

    @Data
    public static class UserDto {

        private String username;

        private String password;

        private String email;

        private String oauth;

        @Builder
        public UserDto(String username, String password, String email, String oauth) {
            super();
            this.username = username;
            this.password = password;
            this.email = email;
            this.oauth = oauth;
        }

        public User toEntity() {
            return User.builder().username(username).password(password).email(email).oauth(oauth).build();
        }

    }

    @Data
    public static class UserUpdateDto {

        private Integer id;

        private String username;

        private String password;

        private String email;

    }

}
