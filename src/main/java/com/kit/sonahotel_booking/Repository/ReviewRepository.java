package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r JOIN r.bookingDetail bd JOIN bd.roomClass rc WHERE rc.id = :roomClassId")
    List<Review> findByRoomClassId(int roomClassId);
}
