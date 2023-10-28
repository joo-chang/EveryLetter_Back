package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.common.service.MailService;
import com.joo.everyletter_back.common.util.CommonUtil;
import com.joo.everyletter_back.common.util.JwtUtil;
import com.joo.everyletter_back.user.dto.UserEmailSendReq;
import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.dto.UserLoginReq;
import com.joo.everyletter_back.user.dto.UserLoginResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;
    private final MailService mailService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;


    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;

    public void emailSend(UserEmailSendReq userEmailSendReq) throws IOException, MessagingException {
        checkDuplicatedEmail(userEmailSendReq.getEmail());
        String title = "[EveryLetter] 회원가입 인증 메일 입니다.";
        String authCode = CommonUtil.createCode();
        String sendMsg = readHtmlFile("mailSend.html");

        sendMsg = sendMsg.replace("[]", authCode);

        mailService.sendEmail(userEmailSendReq.getEmail(), title, sendMsg);

    }
    private static String readHtmlFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        byte[] fileBytes = Files.readAllBytes(resource.getFile().toPath());

        return new String(fileBytes, "UTF-8");
    }
    /**
     * 이미 가입된 이메일인지 확인
     * @param email
     */
    private void checkDuplicatedEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.debug("이미 가입된 이메일 입니다.");
            throw ServiceException.ALREADY_EXIST_EMAIL;
        }
    }
    public void join(UserJoinReq userJoinReq) {

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
