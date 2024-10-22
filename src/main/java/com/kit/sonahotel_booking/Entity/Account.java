package com.kit.sonahotel_booking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String accountId;
  @Column(nullable = false, unique = true)
  String email;
  @Column(nullable = false)
  String password;
  @Column(nullable = false)
  boolean isActivated;
  LocalDate createdAt;
  LocalDate updatedAt;

  @ManyToMany
  @JoinTable(
      name = "account_role",
      joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "accountId"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
  Set<Role> roles;
}
