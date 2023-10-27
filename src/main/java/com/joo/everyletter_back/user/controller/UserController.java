package com.joo.everyletter_back.user.controller;

import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.dto.UserLoginReq;
import com.joo.everyletter_back.user.dto.UserLoginResp;
import com.joo.everyletter_back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiSuccResp<?> join(@RequestBody UserJoinReq userJoinReq) {

        userService.join(userJoinReq);

        return ApiSuccResp.NO_DATA_RESPONSE;
    }

    @PostMapping("/login")
    public ApiSuccResp<UserLoginResp> login(@RequestBody UserLoginReq userLoginReq) {
        UserLoginResp userLoginResp = userService.login(userLoginReq.getEmail(), userLoginReq.getPassword());
        return ApiSuccResp.from(userLoginResp);
    }

}
