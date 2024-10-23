package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
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
public class Promotion {
  @Id
  String promotionId;
  @Column(nullable = false)
  LocalDate startDate;
  @Column(nullable = false)
  LocalDate endDate;
  @Column(nullable = false)
  String promotionName;
  String description;
  boolean status;
  LocalDate createdAt;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "staff_id", nullable = false, referencedColumnName = "staffId")
  Staff staff;

  @OneToMany(mappedBy = "promotion")
  Set<PromotionDetail> promotionDetails;
}
