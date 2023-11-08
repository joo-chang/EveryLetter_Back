package com.joo.everyletter_back.user.controller;

import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.user.dto.UserEmailAuthReq;
import com.joo.everyletter_back.user.dto.UserEmailSendReq;
import com.joo.everyletter_back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join/email/send")
    public ApiSuccResp<?> emailSend(@RequestBody UserEmailSendReq userEmailSendReq) throws IOException, MessagingException {
        userService.emailSend(userEmailSendReq);
        return ApiSuccResp.NO_DATA_RESPONSE;
    }
    @PostMapping("/join/email/auth")
    public ApiSuccResp<?> emailAuth(@RequestBody UserEmailAuthReq userEmailAuthReq) {
        userService.emailAuth(userEmailAuthReq);
        return ApiSuccResp.NO_DATA_RESPONSE;
    }

}
