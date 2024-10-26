package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.response.RoomTypeResponse;

import java.util.List;

public interface IGeneralService<T, E> {
  default T save(GeneralDtoRequest request) {
    return null;
  }

  default T update(GeneralDtoRequest request, E e) {
    return null;
  }

  default void delete(E e) {
  }

  default T get(E e) {
    return null;
  }

  default List<T> getAll() {
    return null;
  }
}

