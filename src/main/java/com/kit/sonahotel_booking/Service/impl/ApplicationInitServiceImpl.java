package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.Staff;
import com.kit.sonahotel_booking.Repository.AccountRepository;
import com.kit.sonahotel_booking.Repository.RoleRepository;
import com.kit.sonahotel_booking.Repository.StaffRepository;
import com.kit.sonahotel_booking.Service.IApplicationInitService;
import com.kit.sonahotel_booking.enums.Roles;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
@Transactional
public class ApplicationInitServiceImpl implements IApplicationInitService {

  AccountRepository accountRepository;
  StaffRepository staffRepository;
  RoleRepository roleRepository;
  PasswordEncoder passwordEncoder;

  @Override
  public void init() {
    if (accountRepository.existsByEmail("nguyentrungk285@gmail.com")) {
      return;
    }
    Account account = Account.builder()
        .email("nguyentrungk285@gmail.com")
        .password(passwordEncoder.encode("admin"))
        .isActivated(true)
        .createdAt(LocalDate.now())
        .roles(new HashSet<>(roleRepository.findAllByRoleName(List.of(Roles.ADMIN.name()))))
        .build();
    Staff staff = Staff.builder()
        .firstName("Trung")
        .lastName("Nguyen")
        .cccd("052203005086")
        .phoneNumber("0378277559")
        .address("Duong 14, Phuong Tang Nhon Phu B, Quan 9, TP.HCM")
        .dob(LocalDate.of(1983, 2, 5))
        .salary(BigDecimal.valueOf(60000000))
        .status(true)
        .gender(true)
        .account(account)
        .build();
    accountRepository.save(account);
    staffRepository.save(staff);
    log.info("Admin account created");
  }
}
