package com.joo.everyletter_back.auth.controller;

import com.joo.everyletter_back.auth.dto.TokenDto;
import com.joo.everyletter_back.auth.dto.TokenRequestDto;
import com.joo.everyletter_back.auth.dto.UserAuthReq;
import com.joo.everyletter_back.auth.dto.UserAuthResp;
import com.joo.everyletter_back.auth.oauth.kakao.KakaoParams;
import com.joo.everyletter_back.auth.oauth.naver.NaverParams;
import com.joo.everyletter_back.auth.service.AuthService;
import com.joo.everyletter_back.auth.service.OAuthService;
import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final OAuthService oauthService;

    @PostMapping("/signup")
    public ApiSuccResp<UserAuthResp> signup(@RequestBody UserAuthReq userAuthReq) {
        return ApiSuccResp.from(authService.signup(userAuthReq));
    }

    @PostMapping("/login")
    public ApiSuccResp<TokenDto> login(@RequestBody UserAuthReq userAuthReq, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(userAuthReq);
        response.addHeader(HttpHeaders.AUTHORIZATION, tokenDto.getAccessToken());
        response.addCookie(SecurityUtil.createCookie("refreshToken", tokenDto.getRefreshToken()));
        return ApiSuccResp.from(tokenDto);
    }

    @PostMapping("/reissue")
    public ApiSuccResp<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    tokenRequestDto.setRefreshToken(cookie.getValue());
                }
            }
        }
        return ApiSuccResp.from(authService.reissue(tokenRequestDto));
    }


    @PostMapping("/oauth/kakao")
    public ApiSuccResp<TokenDto> handleKakaoLogin(@RequestBody KakaoParams kakaoParams, HttpServletResponse response){
        log.debug("넘겨받은 Kakao 인증키 :: " + kakaoParams.getAuthorizationCode());

        TokenDto tokenDto = oauthService.getMemberByOauthLogin(kakaoParams);
        //응답 헤더 생성
        response.addHeader(HttpHeaders.AUTHORIZATION, tokenDto.getAccessToken());
        response.addCookie(SecurityUtil.createCookie("refreshToken", tokenDto.getRefreshToken()));
        return ApiSuccResp.from(tokenDto);
    }

    @PostMapping("/oauth/naver")
    public ApiSuccResp<TokenDto> handleNaverLogin(@RequestBody NaverParams naverParams, HttpServletResponse response){
        log.debug("넘겨받은 naver 인증키 :: " + naverParams.getAuthorizationCode());

        TokenDto tokenDto = oauthService.getMemberByOauthLogin(naverParams);
        //응답 헤더 생성
        response.addHeader(HttpHeaders.AUTHORIZATION, tokenDto.getAccessToken());
        response.addCookie(SecurityUtil.createCookie("refreshToken", tokenDto.getRefreshToken()));

        return ApiSuccResp.from(tokenDto);
    }

}
