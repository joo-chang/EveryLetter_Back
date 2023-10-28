package com.joo.everyletter_back.common.util;

import com.joo.everyletter_back.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 공통 메서드 정의부
 */
@Slf4j
public class CommonUtil {

    /**
     * 랜덤 숫자 String 반환 메서드
     * @return
     */
    public static String createCode(int length) {
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("랜덤 인증 코드 발급 실패");
            throw ServiceException.INTERNAL_SERVER_ERROR;
        }
    }
}
