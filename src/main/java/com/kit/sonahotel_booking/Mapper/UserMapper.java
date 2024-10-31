package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Customer;
import com.kit.sonahotel_booking.Entity.Staff;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.request.UserUpdateRequest;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  Customer toCustomer(UserCreationRequest request);

  Staff toStaff(UserCreationRequest request);

  UserResponse fromCustomertoUserResponse(Customer customer);

  UserResponse fromStafftoUserResponse(Staff staff);

  void updateCustomer(UserUpdateRequest request, @MappingTarget Customer customer);

  void updateStaff(UserUpdateRequest request, @MappingTarget Staff staff);
}
