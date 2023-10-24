package com.joo.everyletter_back.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginReq {
    private String email;
    private String password;
}
