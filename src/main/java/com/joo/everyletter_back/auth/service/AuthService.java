package com.joo.everyletter_back.auth.service;

import com.joo.everyletter_back.auth.dto.*;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.jwt.TokenProvider;
import com.joo.everyletter_back.common.model.entity.EmailAuth;
import com.joo.everyletter_back.common.model.entity.RefreshToken;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.EmailAuthRepository;
import com.joo.everyletter_back.common.model.repository.RefreshTokenRepository;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmailAuthRepository emailAuthRepository;

    @Transactional
    public UserAuthResp signup(UserAuthReq userAuthReq) {
        EmailAuth emailAuth = emailAuthRepository.findByEmail(userAuthReq.getEmail());

        if (emailAuth == null || !emailAuth.isAuthYn()) {
            throw ServiceException.NOT_AUTHENTICATION_YET;
        }

        // 닉네임 중복 재확인
        if (userRepository.existsByNickname(userAuthReq.getNickname())) {
            throw ServiceException.ALREADY_EXIST_NICKNAME;
        }

        User user = userAuthReq.toUser(passwordEncoder);

        userRepository.save(user);
        emailAuthRepository.delete(emailAuth);

        return UserAuthResp.of(userRepository.save(user));
    }

    @Transactional
    public LoginDto login(UserAuthReq userAuthReq) {

        User user = userRepository.findByEmail(userAuthReq.getEmail())
                .orElseThrow(() -> ServiceException.WRONG_EMAIL_OR_PASSWORD);

        // OAuth 로그인 사용자
        if(user.getOauthProvider() != null){
            throw ServiceException.OAUTH_LOGIN_USER;
        }
        // password 틀림
        if (!passwordEncoder.matches(userAuthReq.getPassword(), user.getPassword())) {
            throw ServiceException.WRONG_EMAIL_OR_PASSWORD;
        }

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = userAuthReq.toAuthentication();
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성퓨
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return new LoginDto(tokenDto, user.getId(), user.getEmail(),
                user.getNickname(), user.getProfileUrl(), user.getRole());
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            // 재로그인 해야됨
            throw ServiceException.EXPIRED_REFRESH_TOKEN;
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> ServiceException.EXPIRED_REFRESH_TOKEN);

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw ServiceException.EXPIRED_OR_PREVIOUS_REFRESH_TOKEN;
        }

        // 5. 새로운 토큰 생성
        return tokenProvider.generateAccessToken(authentication);
    }
}