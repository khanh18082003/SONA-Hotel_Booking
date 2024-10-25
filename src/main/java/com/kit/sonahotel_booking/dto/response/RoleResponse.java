package com.kit.sonahotel_booking.dto.response;

import com.kit.sonahotel_booking.Entity.Permission;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class RoleResponse {
  String roleName;
  String roleDescription;
  Set<Permission> permissions;
}
