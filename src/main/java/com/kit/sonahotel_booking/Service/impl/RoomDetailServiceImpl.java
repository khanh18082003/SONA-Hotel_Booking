package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Amenities;
import com.kit.sonahotel_booking.Entity.Review;
import com.kit.sonahotel_booking.Mapper.AmenitiesMapper;
import com.kit.sonahotel_booking.Mapper.ReviewMapper;
import com.kit.sonahotel_booking.Repository.AmenitiesRepository;
import com.kit.sonahotel_booking.Repository.ReviewRepository;
import com.kit.sonahotel_booking.Service.IRoomDetailService;
import com.kit.sonahotel_booking.dto.response.AmenitiesResponse;
import com.kit.sonahotel_booking.dto.response.ReviewResponse;
import com.kit.sonahotel_booking.dto.response.RoomDetailResponse;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomDetailServiceImpl implements IRoomDetailService {
    AmenitiesRepository amenitiesRepository;
    ReviewRepository reviewRepository;
    AmenitiesMapper amenitiesMapper;
    ReviewMapper reviewMapper;
    public Set<AmenitiesResponse> getAmenitiesResponsesByRoomClassId(int id) {
        Set<Amenities> amenities = amenitiesRepository.findAmenitiesByRoomClassId(id);
        return amenities.stream()
                .map(amenitiesMapper::toAmenitiesResponse)
                .collect(Collectors.toSet());
    }

    public List<ReviewResponse> getReviewResponsesByRoomClassId(int id) {
        List<Review> reviews = reviewRepository.findByRoomClassId(id);
        return reviews.stream()
                .map(reviewMapper::toReviewResponse)
                .toList();
    }
    @Override
    public RoomDetailResponse getSearch(SearchResponse searchResponse) {
        Set<AmenitiesResponse> amenitiesResponses = getAmenitiesResponsesByRoomClassId(searchResponse.getId());
        List<ReviewResponse> reviewResponses = getReviewResponsesByRoomClassId(searchResponse.getId());

        return RoomDetailResponse.builder()
                .searchResponse(searchResponse)
                .amenitiesResponse(amenitiesResponses)
                .reviewResponse(reviewResponses)
                .build();
    }
}
