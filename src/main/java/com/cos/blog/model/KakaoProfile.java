package com.cos.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {
	private Integer id;
	private String connectedAt;
	private Properties properties;
	private KakaoAccount kakaoAccount;

	@Data
	public class Properties {
		private String nickname;
		private String profileImage;
		private String thumbnailImage;
	}

	@Data
	public class KakaoAccount {
		private Boolean profileNeedsAgreement;
		private Profile profile;
		private Boolean hasEmail;
		private Boolean emailNeedsAgreement;
		private Boolean isEmailValid;
		private Boolean isEmailVerified;
		private String email;

		@Data
		public class Profile {
			private String nickname;
			private String thumbnailImageUrl;
			private String profileImageUrl;
		}
	}
}
