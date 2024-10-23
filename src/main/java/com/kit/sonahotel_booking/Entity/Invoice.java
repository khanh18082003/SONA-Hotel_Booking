package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
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
public class Invoice {
  @Id
  String invoiceId;
  @Column(nullable = false, precision = 10, scale = 2)
  BigDecimal total;
  @Column(nullable = false)
  boolean status; // true: paid, false: not paid
  String paymentMethod;
  LocalDate createdAt;
  LocalDate updatedAt;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "bookingId")
  Booking booking;

  @PrePersist
  public void generateInvoiceId() {
    this.invoiceId = "INV" + System.currentTimeMillis();
  }

}
