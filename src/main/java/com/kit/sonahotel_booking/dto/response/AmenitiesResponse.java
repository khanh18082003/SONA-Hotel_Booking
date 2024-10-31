package com.kit.sonahotel_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AmenitiesResponse {
    String amenitiesName;
    String amenitiesDescription;
}
