package com.joo.everyletter_back.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 */
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED", "입력값 유효성 검사에 실패하였습니다."),
    NOT_AUTHENTICATION_YET(HttpStatus.BAD_REQUEST, "NOT_AUTHENTICATION_YET", "이메일 인증을 하지 않은 사용자입니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "ALREADY_EXIST_EMAIL", "이미 가입된 이메일입니다."),
    ALREADY_EXIST_NICKNAME(HttpStatus.BAD_REQUEST, "ALREADY_EXIST_NICKNAME", "중복된 닉네임입니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "ALREADY_EXIST_USER", "이미 가입한 회원입니다." ),
    WRONG_BEFORE_PASSWORD(HttpStatus.BAD_REQUEST, "WRONG_BEFORE_PASSWORD", "이전 비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    NOT_EXPIRED_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "NOT_EXPIRED_ACCESS_TOKEN", "만료되지 않은 Access Token입니다."),
    FOLLOW_LIMIT_EXCEED(HttpStatus.BAD_REQUEST, "FOLLOW_LIMIT_EXCEED", "5명을 초과하여 구독 할 수 없습니다."),
    FOLLOWING_SELF_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "FOLLOWING_SELF_NOT_ALLOWED", "자기 자신을 구독 하는 것은 불가능합니다."),
    CONTENT_LIMIT_EXCEED(HttpStatus.BAD_REQUEST,"CONTENT_LIMIT_EXCEED","1000자를 초과하여 입력할 수 없습니다."),
    USER_INFO_NOT_MATCH(HttpStatus.BAD_REQUEST,"USER_INFO_NOT_MATCH","작성자만 내용을 수정할 수 있습니다."),

    /* 401 */
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "ACCESS_DENIED", "유효한 인증 정보가 아닙니다."),
    WRONG_EMAIL_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "WRONG_EMAIL_OR_PASSWORD", "잘못된 이메일 혹은 비밀번호입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "EXPIRED_ACCESS_TOKEN", "Access Token이 만료되었습니다. 토큰을 재발급해주세요"),

    /* 403 */
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "접근할 수 있는 권한이 없습니다."),
    EXPIRED_OR_PREVIOUS_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "EXPIRED_OR_PREVIOUS_REFRESH_TOKEN", "만료되었거나 이전에 발급된 Refresh Token입니다."),

    /* 404 */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "존재하지 않는 유저입니다."),
    FEED_NOT_FOUND(HttpStatus.NOT_FOUND, "FEED_NOT_FOUND", "존재하지 않는 게시물입니다."),
    REPLY_NOT_FOUND(HttpStatus.BAD_REQUEST, "REPLY_NOT_FOUND", "존재하지 않는 댓글입니다."),

    /* 500 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "예상치 못한 서버 에러가 발생했습니다."),
    FAILED_FILE_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_FILE_UPLOAD", "파일 업로드에 실패했습니다."),
    FAILED_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_SEND_EMAIL", "메일 전송에 실패했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
