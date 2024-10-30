package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.dto.response.UserResponse;

public interface ICustomerService extends IGeneralService<UserResponse, String> {
  UserResponse getMyInfo();
}
