package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.EmailAuth;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.EmailAuthRepository;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.common.service.MailService;
import com.joo.everyletter_back.common.util.CommonUtil;
import com.joo.everyletter_back.common.util.JwtUtil;
import com.joo.everyletter_back.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;
    private final MailService mailService;
    private final EmailAuthRepository emailAuthRepository;


    @Value("${jwt.token.secret}")
    private String key;
    @Value("${jwt.token.expiration}")
    private Long expireTimeMs;

    public void emailSend(UserEmailSendReq userEmailSendReq) throws IOException, MessagingException {
        Optional<User> user = userRepository.findByEmail(userEmailSendReq.getEmail());
        if (user.isPresent()) {
            log.debug("이미 가입된 이메일 입니다.");
            throw ServiceException.ALREADY_EXIST_EMAIL;
        }

        String title = "[EveryLetter] 회원가입 인증 메일 입니다.";
        String authCode = CommonUtil.createCode(6);
        String sendMsg = CommonUtil.readHtmlFile("mailSend.html");

        // 해당 위치에 인증 번호 삽입
        sendMsg = sendMsg.replace("[]", authCode);

        // 이메일 전송
        mailService.sendEmail(userEmailSendReq.getEmail(), title, sendMsg);

        // 이전에 인증한 이력이 있으면 Update
        EmailAuth emailAuth = emailAuthRepository.findByEmail(userEmailSendReq.getEmail());

        if (emailAuth != null) {
            emailAuth.setAuthCode(authCode);
            emailAuth.setAuthYn(false);
        }else {
            emailAuth = EmailAuth.builder()
                    .email(userEmailSendReq.getEmail())
                    .authCode(authCode)
                    .authYn(false)
                    .build();
        }

        // 이메일 인증 테이블 저장
        emailAuthRepository.save(emailAuth);

    }

    public void emailAuth(UserEmailAuthReq userEmailAuthReq) {
        EmailAuth emailAuth = emailAuthRepository.findByEmail(userEmailAuthReq.getEmail());

        if (!userEmailAuthReq.getAuthCode().equals(emailAuth.getAuthCode())){
            throw ServiceException.WRONG_EMAIL_AUTHCODE;
        }

        emailAuth.setAuthYn(true);

        emailAuthRepository.save(emailAuth);

    }

    public void nicknameCheck(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw ServiceException.ALREADY_EXIST_NICKNAME;
        }
    }

    public void join(UserJoinReq userJoinReq) {

        EmailAuth emailAuth = emailAuthRepository.findByEmail(userJoinReq.getEmail());

        if (emailAuth == null || !emailAuth.isAuthYn()) {
            throw ServiceException.NOT_AUTHENTICATION_YET;
        }

        // 닉네임 중복 재확인
        nicknameCheck(userJoinReq.getNickname());

        // 저장
        User user = User.builder()
                .email(userJoinReq.getEmail())
                .password(encoder.encode(userJoinReq.getPassword()))
                .nickname(userJoinReq.getNickname())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        emailAuthRepository.delete(emailAuth);

    }

    public UserLoginResp login(UserLoginReq userLoginReq) {
        // email 없음
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
