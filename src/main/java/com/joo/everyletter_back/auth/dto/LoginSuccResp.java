package com.joo.everyletter_back.auth.dto;

import com.joo.everyletter_back.common.enumeration.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginSuccResp {

    private long userId;
    private String email;
    private String nickname;
    private String profileUrl;
    private Role role;

}
