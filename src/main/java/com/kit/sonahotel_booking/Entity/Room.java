package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Room {
  @Id
  int roomNumber;
  @Column(nullable = false)
  int floor;
  String description;
  @Column(nullable = false)
  boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_class_id", nullable = false, referencedColumnName = "id")
  RoomClass roomClass;

  @OneToMany(mappedBy = "room")
  Set<BookingDetailRoom> bookingDetailRooms;
}
