package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
  boolean existsByEmail(String email);

  Optional<Account> findByEmail(String email);
}
