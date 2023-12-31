package com.joo.everyletter_back.auth.oauth.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joo.everyletter_back.auth.oauth.OauthMember;
import com.joo.everyletter_back.auth.oauth.OauthProvider;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverMember implements OauthMember {
	
	@JsonProperty("response") // Naver 응답정보 객체명과 동일
	private Response response; // response와 데이터 매핑을 위한 _사용
	
	//데이터 반환값을 받을 내장클래스
	//필요한 값만 추출하기 위해서 @JsonIgnoreProperties 사용
	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Response{
		private String email;
		private String name;
	}

	@Override
	public String getEmail() {
		return response.email;
	}

	@Override
	public String getNickName() {
		return response.name;
	}

	@Override
	public OauthProvider getOauthProvider() {
		return OauthProvider.NAVER;
	}
}