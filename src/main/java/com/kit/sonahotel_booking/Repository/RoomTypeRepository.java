package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    boolean existsByName(String roomTypeName);
}
