package com.kit.sonahotel_booking.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
  private ErrorCode errorCode;

  public AppException(String message) {
    super(message);
  }

  public AppException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
