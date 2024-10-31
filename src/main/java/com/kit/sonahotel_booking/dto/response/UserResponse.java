package com.kit.sonahotel_booking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kit.sonahotel_booking.Entity.Role;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

  String accountId;
  String email;
  String cccd;
  String firstName;
  String lastName;
  String phoneNumber;
  boolean gender;
  LocalDate dob;
  boolean status;
  String avatar;
  String address;
  BigDecimal salary;
  LocalDate hireDate;
  LocalDate createdAt;
  Set<Role> roles;
}
