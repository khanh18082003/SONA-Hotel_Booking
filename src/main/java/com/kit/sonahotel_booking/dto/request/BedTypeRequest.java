package com.kit.sonahotel_booking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BedTypeRequest extends GeneralDtoRequest{
    String name;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;

}
