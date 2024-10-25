package com.kit.sonahotel_booking.Controller;

import static lombok.AccessLevel.PRIVATE;

import com.kit.sonahotel_booking.Service.ICustomerService;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {
  ICustomerService customerService;

  @PostMapping("/register")
  ApiResponse<UserResponse> register(@RequestBody UserCreationRequest request) {
    UserResponse registerResponse = customerService.save(request);
    return ApiResponse.<UserResponse>builder()
        .code(1005)
        .data(registerResponse)
        .message("Account created successfully")
        .build();
  }
}
