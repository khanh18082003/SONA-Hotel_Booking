package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.util.UUID;
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
public class Image {
  @Id
  String imageId;
  @Column(nullable = false)
  String imageUrl;
  String imageDescription;

  @PrePersist
  public void generateId() {
    this.imageId = "IMG" + UUID.randomUUID().toString();
  }
}
