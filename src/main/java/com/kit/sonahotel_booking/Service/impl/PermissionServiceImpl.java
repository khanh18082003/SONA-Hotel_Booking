package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Permission;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.PermissionMapper;
import com.kit.sonahotel_booking.Repository.PermissionRepository;
import com.kit.sonahotel_booking.Service.IPermissionService;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.PermissionRequest;
import com.kit.sonahotel_booking.dto.response.PermissionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PermissionServiceImpl implements IPermissionService {

  PermissionRepository permissionRepository;
  PermissionMapper permissionMapper;

  @Override
  public PermissionResponse save(GeneralDtoRequest request) {
    PermissionRequest permissionRequest = (PermissionRequest) request;
    if (permissionRepository.existsByPermissionName(permissionRequest.getPermissionName())) {
      throw new AppException(ErrorCode.PERMISSION_EXIST);
    }
    Permission permission = permissionMapper.toPermission(permissionRequest);

    return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
  }

  @Override
  public PermissionResponse update(GeneralDtoRequest request, Integer integer) {
    PermissionRequest permissionRequest = (PermissionRequest) request;
    Permission permission = permissionRepository.findById(integer)
        .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));

    permissionMapper.updatePermissionFromRequest(permissionRequest, permission);

    return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
  }
}
