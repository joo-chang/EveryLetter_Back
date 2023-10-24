package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;
    public String join(String email, String password) {

        // email 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw ServiceException.ALREADY_EXIST_EMAIL;
                });

        // 저장
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);


        return "SUCCESS";
    }

    public String login(String email, String password) {
        // email 없음
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ServiceException.USER_NOT_FOUND);
        // password 틀림
        if (!encoder.matches(password, user.getPassword())) {
            throw ServiceException.WRONG_EMAIL_OR_PASSWORD;
        }

        String token = JwtUtil.createJwt(user.getEmail(), key, expireTimeMs);
        return token;
    }
}
