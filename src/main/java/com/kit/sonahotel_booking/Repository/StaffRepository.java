package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Staff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

  boolean existsByPhoneNumber(String phoneNumber);

  @Query("SELECT s FROM Staff s WHERE s.account.accountId = :accountId")
  Optional<Staff> findByAccountId(String accountId);
}
