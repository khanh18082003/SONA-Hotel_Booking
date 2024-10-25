package com.kit.sonahotel_booking.Controller;

import static lombok.AccessLevel.PRIVATE;

import com.kit.sonahotel_booking.Service.IPermissionService;
import com.kit.sonahotel_booking.dto.request.PermissionRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.PermissionResponse;
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
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PermissionController {

  IPermissionService permissionService;

  @PostMapping("/create")
  ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
    return ApiResponse.<PermissionResponse>builder()
        .code(1001)
        .data(permissionService.save(request))
        .message("Permission created successfully")
        .build();
  }

  @PutMapping("/update/{id}")
  ApiResponse<PermissionResponse> updatePermission(@RequestBody PermissionRequest request,
      @PathVariable int id) {
    return ApiResponse.<PermissionResponse>builder()
        .code(1002)
        .data(permissionService.update(request, id))
        .message("Permission updated successfully")
        .build();
  }

}
