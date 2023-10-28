package com.joo.everyletter_back.user.dto;

import lombok.*;

@Builder
@ToString
@Getter
public class UserLoginResp {
    private String token;
}
