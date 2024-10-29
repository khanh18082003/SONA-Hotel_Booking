package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.dto.request.SearchRequest;

import java.util.List;

public interface ISearchService<T> {
    List<T> searchroom (SearchRequest request);
}
