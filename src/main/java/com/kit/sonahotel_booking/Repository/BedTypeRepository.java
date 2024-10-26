package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.BedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedTypeRepository extends JpaRepository<BedType, Integer> {
    boolean existsByName(String bedTypeName);
}
