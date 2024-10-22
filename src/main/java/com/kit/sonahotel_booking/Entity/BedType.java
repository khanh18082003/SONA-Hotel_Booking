package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
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
public class BedType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @Column(nullable = false, unique = true)
  String name;
  String description;
  LocalDate createdAt;
  LocalDate updatedAt;

  @OneToMany(mappedBy = "bedType")
  List<RoomClass> roomClasses;
}
