package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.RoomType;
import com.kit.sonahotel_booking.dto.request.RoomTypeRequest;
import com.kit.sonahotel_booking.dto.response.RoomTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomTypeResponse toRoomTypeResponse(RoomType roomType);
    RoomType toRoomType(RoomTypeRequest request);

    void updateRoomTypeFromRequest(RoomTypeRequest request,@MappingTarget RoomType roomType);
}
