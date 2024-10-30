package com.kit.sonahotel_booking.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthenticationRequest extends GeneralDtoRequest {
  @Email(message = "INVALID_EMAIL")
  String email;
  String password;
}
