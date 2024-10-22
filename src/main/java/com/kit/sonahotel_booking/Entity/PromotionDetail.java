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
public class PromotionDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  float discountRate;
  @Column(precision = 10, scale = 2)
  BigDecimal discountAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "promotion_id", nullable = false, referencedColumnName = "promotionId")
  Promotion promotion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_class_id", nullable = false, referencedColumnName = "id")
  RoomClass roomClass;
}
