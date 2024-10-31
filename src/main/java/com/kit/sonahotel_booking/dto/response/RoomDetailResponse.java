package com.kit.sonahotel_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDetailResponse{
    SearchResponse searchResponse;
    List<ReviewResponse> reviewResponse;
    Set<AmenitiesResponse> amenitiesResponse;
}
