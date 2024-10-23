package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
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
public class BookingDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(nullable = false)
  int roomQuantity;
  @Column(nullable = false, precision = 10, scale = 2)
  BigDecimal price;
  @Column(nullable = false)
  boolean isReviewed;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "bookingId")
  Booking booking;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_class_id", nullable = false, referencedColumnName = "id")
  RoomClass roomClass;

  @OneToMany(mappedBy = "bookingDetail")
  Set<BookingDetailRoom> bookingDetailRooms;
}
