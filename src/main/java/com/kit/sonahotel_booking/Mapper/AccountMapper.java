package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account toAccount(UserCreationRequest request);
}
