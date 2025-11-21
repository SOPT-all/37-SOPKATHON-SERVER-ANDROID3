package sopt.sopkathon37.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sopt.sopkathon37.global.message.ErrorMessage;
import sopt.sopkathon37.global.response.ApiResponseUtil;
import sopt.sopkathon37.global.response.BaseResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("GlobalExceptionHandler.handleIllegalArgumentException : Illegal Argument Exception : {}", e.getMessage());
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentTypeMismatchException() {
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseResponse<Void>> handleHttpRequestMethodNotSupportedException() {
        return ApiResponseUtil.failure(ErrorMessage.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<BaseResponse<Void>> handleConfetiException(ServerException e) {
        return ApiResponseUtil.failure(e.getErrorMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("GlobalExceptionHandler.handleConstraintViolationException : Constraint Violation Exception : {}", e.getErrorMessage());
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<BaseResponse<Void>> handleConversionFailedException(ConversionFailedException e) {
        log.error("GlobalExceptionHandler.handleConversionFailedException : Conversion Failed Exception : {}", e.getMessage());
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
        log.error("GlobalExceptionHandler.handleException : Unknown Exception : {}", e.getMessage());
        e.printStackTrace();
        return ApiResponseUtil.failure(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}
