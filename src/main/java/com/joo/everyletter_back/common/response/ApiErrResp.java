package com.joo.everyletter_back.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joo.everyletter_back.common.enumeration.ErrorCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiErrResp {

	@JsonProperty("errorCode")
	private String code;
	@JsonProperty("errorMessage")
	private String message;

	public static ApiErrResp from(ErrorCode errorCode) {
		return ApiErrResp.builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.build();
	}

	public void changeMessage(String message) {
		this.message = message;
	}
}
