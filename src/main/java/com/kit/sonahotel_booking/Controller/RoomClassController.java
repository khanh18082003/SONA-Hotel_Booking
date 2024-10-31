package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IRoomClassService;
import com.kit.sonahotel_booking.Service.IRoomDetailService;
import com.kit.sonahotel_booking.dto.request.RoomClassRequest;
import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import com.kit.sonahotel_booking.dto.response.RoomDetailResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/roomclass")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoomClassController {
    IRoomClassService roomClassService;
    IRoomDetailService roomDetailService;
    @NonFinal
    List<SearchResponse> searchResponses;
    @PostMapping("/save")
    ApiResponse<RoomClassResponse> saveRoomClass(@RequestBody RoomClassRequest request){
        return ApiResponse.<RoomClassResponse>builder()
                .code(1001)
                .data(roomClassService.save(request))
                .message("RoomClass created successfully")
                .build();
    }
    @GetMapping("get/{id}")
    ApiResponse<RoomClassResponse> getRoomClass(@PathVariable int id) {
        return ApiResponse.<RoomClassResponse>builder()
                .code(1002)
                .data(roomClassService.get(id))
                .message("RoomClass retrieved successfully")
                .build();
    }

    @GetMapping("/index")
    ApiResponse<List<SearchResponse>> roomList(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate,
                                               @RequestParam("bedName") String bedName, @RequestParam("typeName") String typeName,
                                               @RequestParam("numberOfPerson") int numberOfPerson) {
        SearchRequest request = SearchRequest.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .bedName(bedName)
                .typeName(typeName)
                .numberOfPerson(numberOfPerson)
                .build();
        searchResponses = roomClassService.searchroom(request);
        return ApiResponse.<List<SearchResponse>>builder()
                .code(1004)
                .data(searchResponses)
                .message("RoomType retrieved successfully")
                .build();
    }

    @GetMapping("getRoomDetail/{id}")
    ApiResponse<RoomDetailResponse> getRoomDetailByID(@PathVariable int id) {
        SearchResponse searchResponse = null;
        for (SearchResponse response : searchResponses){
            if (response.getId() == id){
                searchResponse = response;
            }
        }
        return ApiResponse.<RoomDetailResponse>builder()
                .code(1000)
                .data(roomDetailService.getSearch(searchResponse))
                .message("RoomDetail retrieved successfully")
                .build();
    }
}
