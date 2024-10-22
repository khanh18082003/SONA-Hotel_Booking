package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UsingImage {
  @Id
  String relationId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image_id", nullable = false, referencedColumnName = "imageId")
  Image image;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "using_type_id", nullable = false, referencedColumnName = "usingTypeId")
  UsingType usingType;
}
