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
import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public RoomClassResponse get(Integer integer) {
        RoomClass roomClass = roomClassRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_CLASS_NOT_FOUND));
        return roomClassMapper.toRoomClassResponse(roomClass);
    }

    @Override
    public List<RoomClassResponse> getAll() {
        return IRoomClassService.super.getAll();
    }

    @Override
    public List<SearchResponse> searchroom(SearchRequest request) {
        return roomClassRepository.searchRoom(request.getFromDate(),request.getToDate(),request.getBedName(), request.getBedName(), request.getNumberOfPerson());;
    }
}
