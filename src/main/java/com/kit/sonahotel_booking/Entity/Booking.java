package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String bookingId;
  @Column(nullable = false)
  LocalDate fromDate;
  @Column(nullable = false)
  LocalDate toDate;
  @Column(nullable = false)
  int numberOfGuests;
  @Column(nullable = false)
  boolean status;
  @Column(nullable = false)
  boolean paymentStatus;
  @Column(nullable = false, precision = 10, scale = 2)
  BigDecimal totalPrice;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customerId")
  Customer customer;

  @OneToMany(mappedBy = "booking")
  Set<BookingDetail> bookingDetails;
}
