package com.joo.everyletter_back.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiSuccResp<T> {

	public static final ApiSuccResp<?> NO_DATA_RESPONSE = new ApiSuccResp<>();

	private T data;

	public static <T> ApiSuccResp<T> from(T data) {
		return new ApiSuccResp<>(data);
	}
}