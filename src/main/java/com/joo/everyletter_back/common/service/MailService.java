package com.joo.everyletter_back.common.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;

    /**
     * 메일 발송
     * @param toEmail 수신 email
     * @param title 제목
     * @param text 내용
     * @throws MessagingException
     */
    public void sendEmail(String toEmail,
                          String title,
                          String text) throws MessagingException {
        MimeMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.error(e.toString());
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, title, text);
            throw ServiceException.FAILED_SEND_EMAIL;
        }
    }

    // 발신할 이메일 데이터 세팅
    private MimeMessage createEmailForm(String toEmail,
                                             String title,
                                             String text) throws MessagingException {
        MimeMessage mail = emailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mail, false, "UTF-8"); // 2번째 인자는 Multipart여부 결정
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text, true);

        return mail;
    }
}