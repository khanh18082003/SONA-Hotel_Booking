package com.kit.sonahotel_booking.Mapper;

import com.kit.sonahotel_booking.Entity.Review;
import com.kit.sonahotel_booking.dto.response.ReviewResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponse toReviewResponse(Review review);
}
