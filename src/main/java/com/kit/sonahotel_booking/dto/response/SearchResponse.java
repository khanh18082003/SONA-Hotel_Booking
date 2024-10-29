package com.kit.sonahotel_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SearchResponse {
    int id;
    String name;
    BigDecimal pricePerNight;
    int maxOccupancy;
    int availableRoom;
    String nameRoomType;
    String nameBedType;
}
