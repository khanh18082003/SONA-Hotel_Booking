package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Repository.SearchRepository;
import com.kit.sonahotel_booking.Service.ISearchService;
import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchServiceImpl implements ISearchService<SearchResponse> {
    SearchRepository searchRepository;
    @Override
    public List<SearchResponse> searchroom(SearchRequest request) {
        return searchRepository.searchRoom(request.getFromDate(),request.getToDate(),request.getBedName(), request.getBedName(), request.getNumberOfPerson());
    }
}
