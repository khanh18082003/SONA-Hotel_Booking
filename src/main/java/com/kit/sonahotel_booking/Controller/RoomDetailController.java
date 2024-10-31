package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IRoomClassService;
import com.kit.sonahotel_booking.Service.IRoomDetailService;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import com.kit.sonahotel_booking.dto.response.RoomDetailResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/roomDetail")
public class RoomDetailController {


}
