package com.joo.everyletter_back.auth.oauth.naver;

import lombok.Data;

@Data
public class NaverToken {
	private String token_type;
	private String access_token;
	private String refresh_token;
	private int expires_in;
}