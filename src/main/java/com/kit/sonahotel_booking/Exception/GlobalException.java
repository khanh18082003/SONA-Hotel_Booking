package com.kit.sonahotel_booking.Exception;

import com.kit.sonahotel_booking.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(AppException.class)
  ResponseEntity<ApiResponse> handleAppException(AppException e) {
    return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ApiResponse.builder()
        .code(e.getErrorCode().getCode())
        .message(e.getErrorCode().getMessage())
        .build());
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiResponse> handleException(Exception e) {
    return ResponseEntity.badRequest().body(ApiResponse.builder()
        .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
        .message(e.getMessage())
        .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    String errorKey = e.getFieldError().getDefaultMessage();
    ErrorCode errorCode;
    try {
      errorCode = ErrorCode.valueOf(errorKey);
    } catch (IllegalArgumentException ex) {
      errorCode = ErrorCode.INVALID_KEY;
    }
    return ResponseEntity.badRequest().body(ApiResponse.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .build());
  }
}
