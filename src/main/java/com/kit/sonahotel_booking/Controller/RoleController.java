package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IRoleService;
import com.kit.sonahotel_booking.dto.request.RoleRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleController {
  IRoleService roleService;

  @PostMapping("/create")
  ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
    return ApiResponse.<RoleResponse>builder()
        .code(1003)
        .data(roleService.save(request))
        .message("Create role success")
        .build();
  }

  @PutMapping("/update/{id}")
  ApiResponse<RoleResponse> updateRole(@RequestBody RoleRequest request, @PathVariable Integer id) {
    return ApiResponse.<RoleResponse>builder()
        .code(1004)
        .data(roleService.update(request, id))
        .message("Update role success")
        .build();
  }
}
