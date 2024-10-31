package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;

import java.util.List;

public interface IRoomClassService extends IGeneralService<RoomClassResponse, Integer> {
    List<SearchResponse> searchroom (SearchRequest request);
}
