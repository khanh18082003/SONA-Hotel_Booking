package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.RoomClass;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.RoomClassMapper;
import com.kit.sonahotel_booking.Repository.BedTypeRepository;
import com.kit.sonahotel_booking.Repository.RoomClassRepository;
import com.kit.sonahotel_booking.Repository.RoomTypeRepository;
import com.kit.sonahotel_booking.Service.IRoomClassService;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.RoomClassRequest;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomClassServiceImpl implements IRoomClassService {
    RoomClassRepository roomClassRepository;
    RoomClassMapper roomClassMapper;
    BedTypeRepository bedTypeRepository;
    RoomTypeRepository roomTypeRepository;

    @Override
    public RoomClassResponse save(GeneralDtoRequest request){
        RoomClassRequest roomClassRequest = (RoomClassRequest) request;
        if(roomClassRepository.existsByClassName(roomClassRequest.getClassName())){
            throw new AppException(ErrorCode.ROOM_CLASS_EXIST);
        }
        if (!bedTypeRepository.existsByName(roomClassRequest.getBedType().getName())){
            throw new AppException(ErrorCode.BED_TYPE_NOT_FOUND);
        }
        if (!roomTypeRepository.existsByName(roomClassRequest.getRoomType().getName())){
            throw new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND);
        }
        RoomClass roomClass= roomClassMapper.toRoomClass(roomClassRequest);
        return roomClassMapper.toRoomClassResponse(roomClassRepository.save(roomClass));
    }
}