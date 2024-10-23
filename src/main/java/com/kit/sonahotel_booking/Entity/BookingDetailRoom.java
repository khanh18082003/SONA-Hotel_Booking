package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
public class BookingDetailRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  LocalDateTime checkIn;
  LocalDateTime checkOut;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_detail_id", nullable = false, referencedColumnName = "id")
  BookingDetail bookingDetail;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false, referencedColumnName = "roomNumber")
  Room room;
}
