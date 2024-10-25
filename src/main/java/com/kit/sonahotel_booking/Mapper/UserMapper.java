package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Customer;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.request.UserUpdateRequest;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  Customer toCustomer(UserCreationRequest request);

  UserResponse toUserResponse(Customer customer);

  void updateCustomer(UserUpdateRequest request, @MappingTarget Customer customer);
}
