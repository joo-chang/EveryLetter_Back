package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.common.util.JwtUtil;
import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.dto.UserLoginReq;
import com.joo.everyletter_back.user.dto.UserLoginResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;

    public String join(UserJoinReq userJoinReq) {

        // email 중복 체크
        userRepository.findByEmail(userJoinReq.getEmail())
                .ifPresent(user -> {
                    throw ServiceException.ALREADY_EXIST_EMAIL;
                });
        // 저장
        User user = User.builder()
                .email(userJoinReq.getEmail())
                .password(encoder.encode(userJoinReq.getPassword()))
                .nickname(userJoinReq.getNickname())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        return null;
    }

    public UserLoginResp login(UserLoginReq userLoginReq) {
        // email 없음
        log.info(encoder.encode("123qwe)"));
        log.info(encoder.encode("123qwe!"));
        log.info(encoder.encode("123qwe@"));
        log.info(encoder.encode("123qwe#"));
        log.info(encoder.encode("123qwe$"));
        User user = userRepository.findByEmail(userLoginReq.getEmail())
                .orElseThrow(() -> ServiceException.USER_NOT_FOUND);
        // password 틀림
        if (!encoder.matches(userLoginReq.getPassword(), user.getPassword())) {
            throw ServiceException.WRONG_EMAIL_OR_PASSWORD;
        }

        String token = JwtUtil.createJwt(user.getEmail(), key, expireTimeMs);
        return UserLoginResp.builder()
                .token(token)
                .build();
    }

}
