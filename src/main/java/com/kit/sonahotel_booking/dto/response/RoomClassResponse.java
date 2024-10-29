package com.kit.sonahotel_booking.dto.response;

import com.kit.sonahotel_booking.Entity.BedType;
import com.kit.sonahotel_booking.Entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RoomClassResponse {
    String className;
    String description;
    BigDecimal pricePerNight;
    int maxOccupancy;
    RoomType roomType;
    BedType bedType;
    int availableRoom;
}
