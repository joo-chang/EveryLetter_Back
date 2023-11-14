package com.joo.everyletter_back.auth.oauth.naver;

import com.joo.everyletter_back.auth.oauth.OauthParams;
import com.joo.everyletter_back.auth.oauth.OauthProvider;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
public class NaverParams implements OauthParams {
	// Post 요청 시 파라미터로 전달
	private String authorizationCode;
	private String state;
	
	@Override
	public OauthProvider oauthProvider() {
		return OauthProvider.NAVER; // Enum 자료형 지정
	}
	
	@Override
	public MultiValueMap<String, String> makeBody() {
		// Body 지정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("code", authorizationCode);
		body.add("state", state);
		return body;
	}
	
	@Override
	public String getAuthorizationCode() {
		return authorizationCode;
	}
}