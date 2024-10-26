package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IBedTypeService;
import com.kit.sonahotel_booking.dto.request.BedTypeRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.BedTypeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bedtype")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BedTypeController {
    IBedTypeService bedTypeService;
    @PostMapping("/create")
    ApiResponse<BedTypeResponse> createBedType(@RequestBody BedTypeRequest request){
        return ApiResponse.<BedTypeResponse>builder()
                .code(1001)
                .data(bedTypeService.save(request))
                .message("BedType created successfully")
                .build();
    }
    @PutMapping("/update/{id}")
    ApiResponse<BedTypeResponse> updateBedType(@RequestBody BedTypeRequest request, @PathVariable int id){
        return ApiResponse.<BedTypeResponse>builder()
                .code(1002)
                .data(bedTypeService.update(request, id))
                .message("BedType updated successfully")
                .build();
    }
    @GetMapping("/get/{id}")
    ApiResponse<BedTypeResponse> getBedTypeById(@PathVariable int id){
        return ApiResponse.<BedTypeResponse>builder()
                .code(1003)
                .data(bedTypeService.get(id))
                .message("BedType retrieved successfully")
                .build();
    }

    @GetMapping("/getall")
    ApiResponse<List<BedTypeResponse>> getAllBedType() {
        return ApiResponse.<List<BedTypeResponse>>builder()
                .code(1004)
                .data(bedTypeService.getAll())
                .message("BedType retrieved successfully")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    String deleteBedType(@PathVariable int id){
        bedTypeService.delete(id);
        return "BedType has been deleted";
    }
}
