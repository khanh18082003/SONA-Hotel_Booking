package com.kit.sonahotel_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SearchResponse {
    int id;
    String name;
    BigDecimal pricePerNight;
    int maxOccupancy;
    int availableRoom;
    String description;
    String nameRoomType;
    String nameBedType;
}
