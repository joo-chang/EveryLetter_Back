package com.joo.everyletter_back.auth.dto;

import com.joo.everyletter_back.common.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResp {
    private String email;

    public static UserAuthResp of(User user) {
        return new UserAuthResp(user.getEmail());
    }
}