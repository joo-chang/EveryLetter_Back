package com.joo.everyletter_back.auth.dto;

import com.joo.everyletter_back.common.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private TokenDto tokenDto;
    private long userId;
    private String email;
    private String nickname;
    private String profileUrl;
    private Role role;

    public void setUserInfo(long userId, String email, String nickname, String profileUrl, Role role){
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.role = role;
    }

    public void setTokenDto(TokenDto tokenDto) {
        this.tokenDto = tokenDto;
    }
}
