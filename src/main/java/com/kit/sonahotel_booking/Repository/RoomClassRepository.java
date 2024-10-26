package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.RoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomClassRepository extends JpaRepository<RoomClass, Integer> {
    RoomClass findByClassName(String roomClassName);
    boolean existsByClassName(String roomClassName);
}
