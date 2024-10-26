package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.BedType;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.BedTypeMapper;
import com.kit.sonahotel_booking.Repository.BedTypeRepository;
import com.kit.sonahotel_booking.Service.IBedTypeService;
import com.kit.sonahotel_booking.dto.request.BedTypeRequest;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.response.BedTypeResponse;
import com.kit.sonahotel_booking.dto.response.RoomTypeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BedTypeServiceImpl implements IBedTypeService {
    BedTypeRepository bedTypeRepository;
    BedTypeMapper bedTypeMapper;
    @Override
    public BedTypeResponse save(GeneralDtoRequest request){
        BedTypeRequest bedTypeRequest = (BedTypeRequest) request;
        if(bedTypeRepository.existsByName(bedTypeRequest.getName())){
            throw new AppException(ErrorCode.BED_TYPE_EXIST);
        }
        BedType bedType = bedTypeMapper.toBedType(bedTypeRequest);
        return bedTypeMapper.toBedTypeResponse(bedTypeRepository.save(bedType));
    }
    @Override
    public BedTypeResponse update(GeneralDtoRequest request, Integer integer){
        BedTypeRequest bedTypeRequest = (BedTypeRequest) request;
        BedType bedType = bedTypeRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.BED_TYPE_NOT_FOUND));
        bedTypeMapper.updateBedTypeFromRequest(bedTypeRequest, bedType);
        return bedTypeMapper.toBedTypeResponse(bedTypeRepository.save(bedType));
    }

    @Override
    public BedTypeResponse get(Integer integer){
        BedType bedType = bedTypeRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.BED_TYPE_NOT_FOUND));
        return bedTypeMapper.toBedTypeResponse(bedType);
    }
    @Override
    public List<BedTypeResponse> getAll() {
        return bedTypeRepository.findAll().stream()
                .map(bedTypeMapper::toBedTypeResponse)
                .toList(); // Trả về List<BedTypeResponse>
    }

    @Override
    public void delete(Integer integer){
        BedType bedType = bedTypeRepository.findById(integer)
                .orElseThrow(() -> new AppException(ErrorCode.BED_TYPE_NOT_FOUND));
        bedTypeRepository.delete(bedType);
    }

}
