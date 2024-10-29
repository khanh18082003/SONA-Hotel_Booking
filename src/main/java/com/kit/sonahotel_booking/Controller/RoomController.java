package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.ISearchService;
import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {
    ISearchService<SearchResponse> searchService;
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

        return ApiResponse.<List<SearchResponse>>builder()
                .code(1004)
                .data(searchService.searchroom(request))
                .message("RoomType retrieved successfully")
                .build();
    }
}
