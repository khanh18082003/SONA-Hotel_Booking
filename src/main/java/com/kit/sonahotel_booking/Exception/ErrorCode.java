package com.kit.sonahotel_booking.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
  UNCATEGORIZED_EXCEPTION(0, "Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_KEY(-1, "Invalid key", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND(-2, "User not found", HttpStatus.NOT_FOUND),
  EMAIL_ALREADY_EXISTS(-3, "Email already exists", HttpStatus.FOUND),
  INVALID_REQUEST(-3, "Invalid request", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR(-4, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
  UNAUTHORIZED(-5, "You do not have permission", HttpStatus.FORBIDDEN),
  UNAUTHENTICATED(-6, "Unauthenticated", HttpStatus.UNAUTHORIZED),
  SIZE_PASSWORD(-7, "Password must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
  INVALID_EMAIL(-8, "Invalid email", HttpStatus.BAD_REQUEST),
  SIZE_PHONE(-9, "Phone number must be at least {min} characters", HttpStatus.BAD_REQUEST),
  ROLE_NOT_FOUND(-10, "Role not found", HttpStatus.NOT_FOUND),
  INVALID_DATE_OF_BIRTH(-11, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
  PERMISSION_EXIST(-12, "Permission already exists", HttpStatus.FOUND),
  PERMISSION_NOT_FOUND(-13, "Permission not found", HttpStatus.NOT_FOUND),
  ROLE_EXIST(-14, "Role already exists", HttpStatus.FOUND),
  PHONE_NUMBER_ALREADY_EXISTS(-15, "Phone number already exists", HttpStatus.FOUND),;

  private int code;
  private String message;
  private HttpStatusCode statusCode;

  ErrorCode(int code, String message, HttpStatusCode statusCode) {
    this.code = code;
    this.message = message;
    this.statusCode = statusCode;
  }
}
