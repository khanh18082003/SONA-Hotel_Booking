package com.kit.sonahotel_booking.Controller.admin;

import static lombok.AccessLevel.PRIVATE;

import com.kit.sonahotel_booking.Service.IStaffService;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.request.UserUpdateRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StaffController {

  IStaffService staffService;

  @PostMapping("/register")
  ApiResponse<UserResponse> register(@RequestBody UserCreationRequest request) {
    UserResponse registerResponse = staffService.save(request);
    return ApiResponse.<UserResponse>builder()
        .code(1005)
        .data(registerResponse)
        .message("Account created successfully")
        .build();
  }

  @PutMapping("/update/{id}")
  ApiResponse<UserResponse> updateStaff(@RequestBody UserUpdateRequest request,
      @PathVariable String id) {
    UserResponse updateResponse = staffService.update(request, id);
    return ApiResponse.<UserResponse>builder()
        .code(1006)
        .data(updateResponse)
        .message("Account updated successfully")
        .build();
  }

  @DeleteMapping("/delete/{id}")
  ApiResponse<Void> deleteStaff(@PathVariable String id) {
    staffService.delete(id);
    return ApiResponse.<Void>builder()
        .code(1007)
        .message("Account deleted successfully")
        .build();
  }

  @GetMapping("/get/{id}")
  ApiResponse<UserResponse> getStaff(@PathVariable String id) {
    UserResponse staff = staffService.get(id);
    return ApiResponse.<UserResponse>builder()
        .code(1008)
        .data(staff)
        .message("Account retrieved successfully")
        .build();
  }

  @GetMapping("/get-all")
  ApiResponse getAllStaffs() {
    return ApiResponse.builder()
        .code(1009)
        .data(staffService.getAll())
        .message("Accounts retrieved successfully")
        .build();
  }
}
