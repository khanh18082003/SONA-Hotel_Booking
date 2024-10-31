package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Amenities;
import com.kit.sonahotel_booking.dto.response.AmenitiesResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmenitiesMapper {
    AmenitiesResponse toAmenitiesResponse(Amenities amenities);
}
