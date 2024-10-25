package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Role;
import com.kit.sonahotel_booking.dto.request.RoleRequest;
import com.kit.sonahotel_booking.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
  @Mapping(target = "permissions", ignore = true)
  Role toRole(RoleRequest roleRequest);

  RoleResponse toRoleResponse(Role role);

  @Mapping(target = "permissions", ignore = true)
  void updateRoleFromRequest(RoleRequest roleRequest, @MappingTarget Role role);

}
