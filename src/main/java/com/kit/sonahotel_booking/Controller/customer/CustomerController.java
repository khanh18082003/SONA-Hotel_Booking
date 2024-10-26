package com.kit.sonahotel_booking.Controller.customer;

import static lombok.AccessLevel.PRIVATE;

import com.kit.sonahotel_booking.Service.ICustomerService;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.request.UserUpdateRequest;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import java.util.List;
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

  @PutMapping("/update/{id}")
  ApiResponse<UserResponse> updateCustomer(@RequestBody UserUpdateRequest request, @PathVariable String id) {
    UserResponse updateResponse = customerService.update(request, id);
    return ApiResponse.<UserResponse>builder()
        .code(1006)
        .data(updateResponse)
        .message("Account updated successfully")
        .build();
  }

  @DeleteMapping("/delete/{id}")
  ApiResponse<Void> deleteCustomer(@PathVariable String id) {
    customerService.delete(id);
    return ApiResponse.<Void>builder()
        .code(1007)
        .message("Account deleted successfully")
        .build();
  }

  @GetMapping("/get/{id}")
  ApiResponse<UserResponse> getCustomer(@PathVariable String id) {
    UserResponse customer = customerService.get(id);
    return ApiResponse.<UserResponse>builder()
        .code(1008)
        .data(customer)
        .message("Account retrieved successfully")
        .build();
  }

  @GetMapping("/get-all")
  ApiResponse getAllCustomers() {
    List<UserResponse> customers = customerService.getAll();
    return ApiResponse.builder()
        .code(1009)
        .data(customers)
        .message("All accounts retrieved successfully")
        .build();
  }
}
