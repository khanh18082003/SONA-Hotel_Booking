package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
  boolean existsByPhoneNumber(String phoneNumber);
}
