package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.Entity.RoomClass;
import com.kit.sonahotel_booking.dto.response.RoomDetailResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;

public interface IRoomDetailService extends IGeneralService<RoomDetailResponse, Integer> {
    public RoomDetailResponse getSearch(SearchResponse searchResponse);
}
