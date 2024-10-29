package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.RoomClass;
import com.kit.sonahotel_booking.dto.request.SearchRequest;
import com.kit.sonahotel_booking.dto.response.RoomClassResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomClassRepository extends JpaRepository<RoomClass, Integer> {
    RoomClass findByClassName(String roomClassName);
    boolean existsByClassName(String roomClassName);
}
