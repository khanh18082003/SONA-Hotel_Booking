package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
public class Review {
  @Id
  String reviewId;
  @Column(nullable = false)
  String reviewContent;
  @Column(nullable = false)
  int rating;
  LocalDate createdAt;
  LocalDate updatedAt;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_detail_id", nullable = false, referencedColumnName = "id")
  BookingDetail bookingDetail;

  @PrePersist
  public void generateReviewId() {
    this.reviewId = "REV" + System.currentTimeMillis();
  }
}
