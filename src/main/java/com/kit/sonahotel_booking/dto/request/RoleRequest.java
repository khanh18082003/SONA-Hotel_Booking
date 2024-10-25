package com.kit.sonahotel_booking.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RoleRequest extends GeneralDtoRequest {
  String roleName;
  String roleDescription;
  List<String> permissions;
}
