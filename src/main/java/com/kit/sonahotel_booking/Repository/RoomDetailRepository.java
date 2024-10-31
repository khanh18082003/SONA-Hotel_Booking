package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.dto.response.RoomDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDetailRepository extends JpaRepository<RoomDetailResponse, Integer> {

}
