package com.kit.sonahotel_booking.dto.request;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserUpdateRequest extends GeneralDtoRequest {
  String firstName;
  String lastName;
  @Size(min = 10, max = 11, message = "SIZE_PHONE")
  String phoneNumber;
  String address;
  String avatar;
  @Size(min= 12, max = 12, message = "SIZE_CCCD")
  String cccd;
  BigDecimal salary;
  LocalDate dob;
  boolean gender;
}
