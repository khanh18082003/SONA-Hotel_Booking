package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
  boolean existsByPhoneNumber(String phoneNumber);

  @Query("SELECT c FROM Customer c WHERE c.account.accountId = :accountId")
  Optional<Customer> findByAccountId(String accountId);
}
