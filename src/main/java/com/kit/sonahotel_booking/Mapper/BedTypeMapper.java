package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.BedType;
import com.kit.sonahotel_booking.dto.request.BedTypeRequest;
import com.kit.sonahotel_booking.dto.response.BedTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BedTypeMapper {
    BedType toBedType(BedTypeRequest bedTypeRequest);
    void updateBedTypeFromRequest(BedTypeRequest bedTypeRequest,@MappingTarget BedType bedType);

    BedTypeResponse toBedTypeResponse(BedType bedType);
}
