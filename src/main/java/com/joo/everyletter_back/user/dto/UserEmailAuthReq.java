package com.joo.everyletter_back.user.dto;

import lombok.Getter;

@Getter
public class UserEmailAuthReq {
    private String email;
    private String authCode;
}