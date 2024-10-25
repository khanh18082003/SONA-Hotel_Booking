package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String customerId;
  @Column(nullable = false)
  String firstName;
  @Column(nullable = false)
  String lastName;
  @Column(nullable = false, unique = true)
  String phoneNumber;
  String cccd;
  @Column(nullable = false)
  boolean gender;
  @Column(nullable = false)
  LocalDate dob;
  LocalDate updatedAt;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false, unique = true, referencedColumnName = "accountId")
  Account account;

  @OneToMany(mappedBy = "customer")
  Set<Booking> bookings;
}
