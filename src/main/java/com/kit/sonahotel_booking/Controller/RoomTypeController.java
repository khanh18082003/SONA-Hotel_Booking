package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IRoomTypeService;
import com.kit.sonahotel_booking.dto.request.RoomTypeRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoomTypeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomtype")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeController {
    IRoomTypeService roomTypeService;
    @PostMapping("/create")
    ApiResponse<RoomTypeResponse> createRoomType(@RequestBody RoomTypeRequest request){
        return ApiResponse.<RoomTypeResponse>builder()
                .code(1001)
                .data(roomTypeService.save(request))
                .message("RoomType created successfully")
                .build();
    }
    @PutMapping("/update/{id}")
    ApiResponse<RoomTypeResponse> updateRoomType(@RequestBody RoomTypeRequest request, @PathVariable int id){
        return ApiResponse.<RoomTypeResponse>builder()
                .code(1002)
                .data(roomTypeService.update(request, id))
                .message("RoomType updated successfully")
                .build();
    }
    @GetMapping("/get/{id}")
    ApiResponse<RoomTypeResponse> getRoomTypeById(@PathVariable int id){
        return ApiResponse.<RoomTypeResponse>builder()
                .code(1003)
                .data(roomTypeService.get(id))
                .message("RoomType retrieved successfully")
                .build();
    }

    @GetMapping("/getall")
    ApiResponse<List<RoomTypeResponse>> getAllRoomType() {
        return ApiResponse.<List<RoomTypeResponse>>builder()
                .code(1004)
                .data(roomTypeService.getAll())
                .message("RoomType retrieved successfully")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    String DeleteRoomType(@PathVariable int id){
        roomTypeService.delete(id);
        return "RoomType has been deleted";
    }
}
