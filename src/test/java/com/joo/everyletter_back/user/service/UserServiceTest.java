package com.joo.everyletter_back.user.service;

import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.EmailAuth;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.EmailAuthRepository;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.user.dto.UserEmailSendReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailAuthRepository emailAuthRepository;

    @Test
    @DisplayName("이메일 발송")
    public void emailSend() throws MessagingException, IOException {
        //given
        UserEmailSendReq userEmailSendReq = new UserEmailSendReq();
        userEmailSendReq.setEmail("jooc0311@naver.com");
        //when
        userService.emailSend(userEmailSendReq);

        //then
        EmailAuth emailAuth = emailAuthRepository.findByEmail(userEmailSendReq.getEmail());

        Assertions.assertEquals(emailAuth.getEmail(), userEmailSendReq.getEmail());
    }
    @Test
    @DisplayName("이메일 발송 실패")
    public void emailSendFail() throws MessagingException, IOException {
        //given
        userRepository.save(User.builder()
                .email("jooc0311@naver.com")
                .role(Role.ROLE_USER)
                .password("test123")
                .nickname("익명0123")
                .subLimit(5)
                .build());
        UserEmailSendReq userEmailSendReq = new UserEmailSendReq();
        userEmailSendReq.setEmail("jooc0311@naver.com");
        //when
        try {
            userService.emailSend(userEmailSendReq);
        } catch (ServiceException e) {
            // 이미 가입된 이메일
            //then
            System.out.println(e);
            Assertions.assertEquals(e, ServiceException.ALREADY_EXIST_EMAIL);
            return;
        }

        Assertions.assertEquals(userEmailSendReq.getEmail(), "jooc0312@naver.com");
    }
}