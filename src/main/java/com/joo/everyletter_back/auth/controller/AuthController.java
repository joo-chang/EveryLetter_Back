package com.joo.everyletter_back.auth.controller;

import com.joo.everyletter_back.auth.dto.TokenDto;
import com.joo.everyletter_back.auth.dto.TokenRequestDto;
import com.joo.everyletter_back.auth.dto.UserAuthReq;
import com.joo.everyletter_back.auth.dto.UserAuthResp;
import com.joo.everyletter_back.auth.service.AuthService;
import com.joo.everyletter_back.common.response.ApiSuccResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiSuccResp<UserAuthResp> signup(@RequestBody UserAuthReq userAuthReq) {
        return ApiSuccResp.from(authService.signup(userAuthReq));
    }

    @PostMapping("/login")
    public ApiSuccResp<TokenDto> login(@RequestBody UserAuthReq userAuthReq, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(userAuthReq);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDto.getAccessToken());
        return ApiSuccResp.from(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

}
