package com.joo.everyletter_back.common.exception;

import com.joo.everyletter_back.common.enumeration.ErrorCode;
import com.joo.everyletter_back.common.response.ApiErrResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    /**
     * 모든 ServiceException 처리 핸들러
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ApiErrResp> handleBaseException(ServiceException e) {
        log.error(e.getErrorCode().toString());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiErrResp.from(e.getErrorCode()));
    }

    // @Valid bind 오류 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiErrResp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> params = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            params.add(error.getField() + ": " + error.getDefaultMessage());
        }
        String errMsg = String.join(", ", params);

        ApiErrResp resp = ApiErrResp.from(ErrorCode.VALIDATION_FAILED);
        resp.changeMessage(errMsg);

        return resp;
    }

    /**
     * RuntimeException 발생 시 ServiceException 처리
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    protected ApiErrResp handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        log.error(e.toString());
        return ApiErrResp.from(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
