package com.cos.blog.model;

import lombok.Data;

@Data
public class OAuthToken {
	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private int expiresIn;
	private String scope;
	private int refreshTokenExpiresIn;
}
