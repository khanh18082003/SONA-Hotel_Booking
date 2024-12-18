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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String staffId;

  @Column(nullable = false)
  String firstName;

  @Column(nullable = false)
  String lastName;

  @Column(nullable = false, unique = true)
  String cccd;

  @Column(nullable = false, unique = true)
  String phoneNumber;

  String address;

  @Column(nullable = false)
  LocalDate dob;

  LocalDate hireDate;
  LocalDate updatedAt;

  @Column(precision = 10, scale = 2)
  BigDecimal salary;

  @Column(nullable = false)
  boolean status;

  @Column(nullable = false)
  boolean gender;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false, unique = true, referencedColumnName = "accountId")
  Account account;

  @OneToMany(mappedBy = "staff")
  Set<Promotion> promotions;
}
