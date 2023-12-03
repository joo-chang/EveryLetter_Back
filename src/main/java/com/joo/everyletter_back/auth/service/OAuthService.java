package com.joo.everyletter_back.auth.service;

import com.joo.everyletter_back.auth.dto.TokenDto;
import com.joo.everyletter_back.auth.oauth.OauthMember;
import com.joo.everyletter_back.auth.oauth.OauthParams;
import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.jwt.TokenProvider;
import com.joo.everyletter_back.common.model.entity.RefreshToken;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.RefreshTokenRepository;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final RequestOauthInfoService requestOauthInfoService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    // 받아온 유저정보로 로그인 시도
    public TokenDto getMemberByOauthLogin(OauthParams oauthParams) {
        log.debug("------ Oauth 로그인 시도 ------");

        // 인증 파라미터 객체를 이용하여 해당 enum클래스에 해당하는 메소드 수행
        OauthMember oauthMember = requestOauthInfoService.request(oauthParams);
        log.debug("전달받은 유저정보:: " + oauthMember.getEmail());

        // 획득된 회원정보 DB 조회
        Optional<User> user = userRepository.findByEmail(oauthMember.getEmail());

        if (user.isEmpty()) {
            log.info("------ 회원가입 필요한 회원 ------");
            // 회원가입이 되지 않은 회원이기 때문에 회원 DTO에 값을 전달하여 DB저장
            log.info("회원가입 요청 :: " + oauthMember.getEmail());

            User topUser = userRepository.findTopByOrderByCreatedDateDesc();

            // kakaoMember에서 전달된 데이터를 가진 memberDTO DB 저장
            userRepository.save(User.builder()
                            .email(oauthMember.getEmail())
                            .nickname("익명" + String.format("%05d", topUser.getId() + 1))
                            .password(passwordEncoder.encode(oauthParams.oauthProvider().toString()))
                            .role(Role.ROLE_USER)
                            .oauthProvider(oauthParams.oauthProvider())
                            .build());

            log.debug("회원가입 완료 :: " + oauthMember.getEmail());
        } else if (user.get().getOauthProvider() != oauthParams.oauthProvider()) {
            throw ServiceException.ALREADY_EXIST_USER;
        }

        // 이미 가입된 회원은 토큰발급
        log.debug("------ JWT 발급 ------");



        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(oauthMember.getEmail(), oauthParams.oauthProvider().toString());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        log.debug("------ JWT 발급완료 ------");
        return tokenDto;
    }
}
