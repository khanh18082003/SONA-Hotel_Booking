package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.RoomClass;
import com.kit.sonahotel_booking.dto.request.RoomClassRequest;
import com.kit.sonahotel_booking.dto.request.RoomTypeRequest;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomClassMapper {
    RoomClass toRoomClass(RoomClassRequest request);

    void updateRoomClassFromRequest(RoomTypeRequest request, @MappingTarget RoomClass roomClass);

    RoomClassResponse toRoomClassResponse(RoomClass roomClass);
}
