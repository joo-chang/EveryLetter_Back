package com.joo.everyletter_back.common.exception;

import com.joo.everyletter_back.common.enumeration.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException{

    public static final ServiceException NOT_AUTHENTICATION_YET = new ServiceException(ErrorCode.NOT_AUTHENTICATION_YET);
    public static final ServiceException ALREADY_EXIST_EMAIL = new ServiceException(ErrorCode.ALREADY_EXIST_EMAIL);
    public static final ServiceException ALREADY_EXIST_NICKNAME = new ServiceException(ErrorCode.ALREADY_EXIST_NICKNAME);
    public static final ServiceException REPLY_NOT_FOUND = new ServiceException(ErrorCode.REPLY_NOT_FOUND);
    public static final ServiceException CONTENT_LIMIT_EXCEED = new ServiceException(ErrorCode.CONTENT_LIMIT_EXCEED);
    public static final ServiceException USER_INFO_NOT_MATCH = new ServiceException(ErrorCode.USER_INFO_NOT_MATCH);
    public static final ServiceException WRONG_EMAIL_OR_PASSWORD = new ServiceException(ErrorCode.WRONG_EMAIL_OR_PASSWORD);
    public static final ServiceException WRONG_BEFORE_PASSWORD = new ServiceException(ErrorCode.WRONG_BEFORE_PASSWORD);
    public static final ServiceException NOT_EXPIRED_ACCESS_TOKEN = new ServiceException(ErrorCode.NOT_EXPIRED_ACCESS_TOKEN);
    public static final ServiceException EXPIRED_ACCESS_TOKEN = new ServiceException(ErrorCode.EXPIRED_ACCESS_TOKEN);
    public static final ServiceException EXPIRED_REFRESH_TOKEN = new ServiceException(ErrorCode.EXPIRED_REFRESH_TOKEN);
    public static final ServiceException INVALID_TOKEN = new ServiceException(ErrorCode.INVALID_TOKEN);
    public static final ServiceException FORBIDDEN = new ServiceException(ErrorCode.FORBIDDEN);
    public static final ServiceException EXPIRED_OR_PREVIOUS_REFRESH_TOKEN = new ServiceException(ErrorCode.EXPIRED_OR_PREVIOUS_REFRESH_TOKEN);
    public static final ServiceException USER_NOT_FOUND = new ServiceException(ErrorCode.USER_NOT_FOUND);
    public static final ServiceException FEED_NOT_FOUND = new ServiceException(ErrorCode.FEED_NOT_FOUND);
    public static final ServiceException FAILED_FILE_UPLOAD = new ServiceException(ErrorCode.FAILED_FILE_UPLOAD);
    public static final ServiceException INTERNAL_SERVER_ERROR = new ServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
    public static final ServiceException FAILED_SEND_EMAIL = new ServiceException(ErrorCode.FAILED_SEND_EMAIL);
    public static final ServiceException FOLLOW_LIMIT_EXCEED = new ServiceException(ErrorCode.FOLLOW_LIMIT_EXCEED);
    public static final ServiceException FOLLOWING_SELF_NOT_ALLOWED = new ServiceException(ErrorCode.FOLLOWING_SELF_NOT_ALLOWED);
    public static final ServiceException ALREADY_EXIST_USER = new ServiceException(ErrorCode.ALREADY_EXIST_USER);
    public static final ServiceException WRONG_EMAIL_AUTHCODE = new ServiceException(ErrorCode.WRONG_EMAIL_AUTHCODE);
    public static final ServiceException OAUTH_LOGIN_USER = new ServiceException(ErrorCode.OAUTH_LOGIN_USER);
    public static final ServiceException CATEGORY_NOT_FOUND = new ServiceException(ErrorCode.CATEGORY_NOT_FOUND);
    public static final ServiceException POST_NOT_FOUND = new ServiceException(ErrorCode.POST_NOT_FOUND);

    private final ErrorCode errorCode;

    // 의도적인 예외이므로 stack trace 제거 (불필요한 예외처리 비용 제거)
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
