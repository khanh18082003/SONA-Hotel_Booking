package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Permission;
import com.kit.sonahotel_booking.dto.request.PermissionRequest;
import com.kit.sonahotel_booking.dto.response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

  Permission toPermission(PermissionRequest permission);

  PermissionResponse toPermissionResponse(Permission permission);

  void updatePermissionFromRequest(PermissionRequest permissionRequest,
      @MappingTarget Permission permission);
}
