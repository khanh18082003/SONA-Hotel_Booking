package com.kit.sonahotel_booking.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserCreationRequest extends GeneralDtoRequest {
  @Email(message = "INVALID_EMAIL")
  String email;
  @Size(min = 8, max = 20, message = "SIZE_PASSWORD")
  String password;
  String firstName;
  String lastName;
  @Size(min = 10, max = 11, message = "SIZE_PHONE")
  String phoneNumber;
  @Size(min = 12, max = 12, message = "SIZE_CCCD")
  String cccd;
  boolean gender;
  LocalDate dob;
  LocalDate hireDate;
}
