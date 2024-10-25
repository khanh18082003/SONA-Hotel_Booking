package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Role;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.RoleMapper;
import com.kit.sonahotel_booking.Repository.PermissionRepository;
import com.kit.sonahotel_booking.Repository.RoleRepository;
import com.kit.sonahotel_booking.Service.IRoleService;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.RoleRequest;
import com.kit.sonahotel_booking.dto.response.RoleResponse;
import java.util.HashSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements IRoleService {

  RoleRepository roleRepository;
  PermissionRepository permissionRepository;
  RoleMapper roleMapper;

  @Override
  public RoleResponse update(GeneralDtoRequest request, Integer id) {
    RoleRequest roleRequest = (RoleRequest) request;
    Role role = roleRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
    roleMapper.updateRoleFromRequest(roleRequest, role);
    var permissions = permissionRepository.findAllByPermissionName(roleRequest.getPermissions());
    role.setPermissions(new HashSet<>(permissions));

    return roleMapper.toRoleResponse(roleRepository.save(role));
  }

  @Override
  public RoleResponse save(GeneralDtoRequest request) {
    RoleRequest roleRequest = (RoleRequest) request;
    if (roleRepository.existsByRoleName(roleRequest.getRoleName())) {
      throw new AppException(ErrorCode.ROLE_EXIST);
    }
    Role role = roleMapper.toRole(roleRequest);
    var permissions = permissionRepository.findAllByPermissionName(roleRequest.getPermissions());
    role.setPermissions(new HashSet<>(permissions));

    return roleMapper.toRoleResponse(roleRepository.save(role));
  }
}
