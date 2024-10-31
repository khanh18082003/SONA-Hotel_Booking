package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities,Integer> {
    @Query("SELECT rc.amenities FROM RoomClass rc  WHERE rc.id = :roomClassId")
    Set<Amenities> findAmenitiesByRoomClassId(int roomClassId);
}
