package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IRoomClassService;
import com.kit.sonahotel_booking.dto.request.RoomClassRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomclass")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoomClassController {
    IRoomClassService roomClassService;
    @PostMapping("/save")
    ApiResponse<RoomClassResponse> saveRoomClass(@RequestBody RoomClassRequest request){
        return ApiResponse.<RoomClassResponse>builder()
                .code(1001)
                .data(roomClassService.save(request))
                .message("RoomClass created successfully")
                .build();
    }
}
