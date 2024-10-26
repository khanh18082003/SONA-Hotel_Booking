package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.RoomType;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.RoomTypeMapper;
import com.kit.sonahotel_booking.Repository.RoomTypeRepository;
import com.kit.sonahotel_booking.Service.IRoomTypeService;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.RoomTypeRequest;
import com.kit.sonahotel_booking.dto.response.RoomTypeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeServiceImpl implements IRoomTypeService {
    RoomTypeRepository roomTypeRepository;
    RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeResponse save(GeneralDtoRequest request){
        RoomTypeRequest roomTypeRequest = (RoomTypeRequest) request;
        System.out.println(roomTypeRequest.toString());
        if(roomTypeRepository.existsByName(roomTypeRequest.getName())){
            throw new AppException(ErrorCode.ROOM_TYPE_EXIST);
        }
        RoomType roomType = roomTypeMapper.toRoomType(roomTypeRequest);
        return roomTypeMapper.toRoomTypeResponse(roomTypeRepository.save(roomType));
    }
    @Override
    public RoomTypeResponse update(GeneralDtoRequest request, Integer integer){
            RoomTypeRequest roomTypeRequest = (RoomTypeRequest) request;
        System.out.println("abc"+roomTypeRequest.toString());
            RoomType roomType = roomTypeRepository.findById(integer)
                    .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
            roomTypeMapper.updateRoomTypeFromRequest(roomTypeRequest, roomType);
            return roomTypeMapper.toRoomTypeResponse(roomTypeRepository.save(roomType));
    }

    @Override
    public RoomTypeResponse get(Integer integer){
        RoomType roomType = roomTypeRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
        return roomTypeMapper.toRoomTypeResponse(roomType);
    }

    @Override
    public void delete(Integer integer){
        RoomType roomType = roomTypeRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
        roomTypeRepository.delete(roomType);
    }

    @Override
    public List<RoomTypeResponse> getAll(){
        return roomTypeRepository.findAll().stream()
                .map(roomTypeMapper::toRoomTypeResponse)
                .toList();
    }
}
