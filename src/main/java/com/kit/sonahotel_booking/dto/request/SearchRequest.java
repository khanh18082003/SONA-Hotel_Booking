package com.kit.sonahotel_booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SearchRequest {
    LocalDate fromDate;
    LocalDate toDate;
    String bedName;
    String typeName;
    int numberOfPerson;
}
