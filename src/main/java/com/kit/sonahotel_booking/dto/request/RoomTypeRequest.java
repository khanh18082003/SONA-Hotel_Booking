package com.kit.sonahotel_booking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RoomTypeRequest extends GeneralDtoRequest{
    String name;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
}
