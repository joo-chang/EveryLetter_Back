package com.joo.everyletter_back.common.util;

import com.joo.everyletter_back.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class CommonUtil {
    public static String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("랜덤 인증 코드 발급 실패");
            throw ServiceException.INTERNAL_SERVER_ERROR;
        }
    }
}
