package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class BookingDetailService {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(nullable = false)
  int quantity;
  @Column(nullable = false, precision = 10, scale = 2)
  BigDecimal price;
  boolean status; // true: paid, false: not paid
  LocalDate createdAt;
  LocalDate updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_detail_id", nullable = false, referencedColumnName = "id")
  BookingDetail bookingDetail;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_id", nullable = false, referencedColumnName = "id")
  Service service;
}
