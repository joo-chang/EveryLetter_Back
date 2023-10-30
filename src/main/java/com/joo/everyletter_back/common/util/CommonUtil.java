package com.joo.everyletter_back.common.util;

import com.joo.everyletter_back.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
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
            StringBuilder randomNumber = new StringBuilder();
            for (int i = 0; i < length; i++) {
                randomNumber.append(random.nextInt(10));
            }
            log.info("인증 코드 : {}", randomNumber);
            return randomNumber.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("랜덤 인증 코드 발급 실패");
            throw ServiceException.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 파일 경로 입력 시 읽어서 String으로 반환
     * @param filePath
     * @return 파일 내용 string 반환
     * @throws IOException
     */
    public static String readHtmlFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        byte[] fileBytes = Files.readAllBytes(resource.getFile().toPath());

        return new String(fileBytes, "UTF-8");
    }
}
