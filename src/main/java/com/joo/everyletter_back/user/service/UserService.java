package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

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
}
