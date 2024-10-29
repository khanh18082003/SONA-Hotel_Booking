package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.RoomType;
import com.kit.sonahotel_booking.dto.response.SearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SearchRepository extends JpaRepository<RoomType, Integer> {
    @Query(value = "CALL searchRoom(:fromDate, :toDate, :bedName, :typeName, :numberOfPerson)", nativeQuery = true)
    List<SearchResponse> searchRoom(LocalDate fromDate, LocalDate toDate,
                                    String bedName, String typeName,
                                    int numberOfPerson);
}
