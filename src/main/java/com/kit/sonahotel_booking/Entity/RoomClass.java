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
public class RoomClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @Column(nullable = false, unique = true)
  String className;
  @Column(nullable = false)
  String description;
  @Column(nullable = false, precision = 10, scale = 2)
  BigDecimal pricePerNight;
  @Column(nullable = false)
  int maxOccupancy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_type_id", nullable = false, referencedColumnName = "id")
  RoomType roomType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bed_type_id", nullable = false, referencedColumnName = "id")
  BedType bedType;

  @ManyToMany
  @JoinTable(
      name = "room_class_amenities",
      joinColumns = @JoinColumn(name = "room_class_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "amenitiesId")
  )
  Set<Amenities> amenities;

  @OneToMany(mappedBy = "roomClass")
  Set<BookingDetail> bookingDetails;

  @OneToMany(mappedBy = "roomClass")
  Set<Room> rooms;

  @OneToMany(mappedBy = "roomClass")
  Set<PromotionDetail> promotionDetails;
}
