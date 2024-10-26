package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.Staff;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.AccountMapper;
import com.kit.sonahotel_booking.Mapper.UserMapper;
import com.kit.sonahotel_booking.Repository.AccountRepository;
import com.kit.sonahotel_booking.Repository.RoleRepository;
import com.kit.sonahotel_booking.Repository.StaffRepository;
import com.kit.sonahotel_booking.Service.IStaffService;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.UserCreationRequest;
import com.kit.sonahotel_booking.dto.request.UserUpdateRequest;
import com.kit.sonahotel_booking.dto.response.UserResponse;
import com.kit.sonahotel_booking.enums.Roles;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class StaffServiceImpl implements IStaffService {

  PasswordEncoder passwordEncoder;
  StaffRepository staffRepository;
  AccountRepository accountRepository;
  AccountMapper accountMapper;
  UserMapper userMapper;
  RoleRepository roleRepository;

  public UserResponse save(GeneralDtoRequest request) {
    UserCreationRequest userCreationRequest = (UserCreationRequest) request;
    if (accountRepository.existsByEmail(userCreationRequest.getEmail())) {
      throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
    if (staffRepository.existsByPhoneNumber(userCreationRequest.getPhoneNumber())) {
      throw new AppException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

    Account account = accountMapper.toAccount(userCreationRequest);
    var roles = roleRepository.findAllByRoleName(List.of(Roles.STAFF.name()));
    account.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
    account.setActivated(true);
    account.setCreatedAt(LocalDate.now());
    account.setRoles(new HashSet<>(roles));
    accountRepository.save(account);

    Staff staff = userMapper.toStaff(userCreationRequest);
    staff.setAccount(account);
    staff.setStatus(true);
    UserResponse userResponse = userMapper.fromStafftoUserResponse(staffRepository.save(staff));
    userResponse.setAccountId(account.getAccountId());
    userResponse.setEmail(account.getEmail());
    userResponse.setStatus(account.isActivated());
    userResponse.setCreatedAt(account.getCreatedAt());
    userResponse.setRoles(account.getRoles());
    return userResponse;
  }

  public UserResponse update(GeneralDtoRequest request, String accountId) {
    UserUpdateRequest userUpdateRequest = (UserUpdateRequest) request;

    Staff staff = staffRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

    userMapper.updateStaff(userUpdateRequest, staff);
    staffRepository.save(staff);

    UserResponse userResponse = userMapper.fromStafftoUserResponse(staff);
    userResponse.setAccountId(staff.getAccount().getAccountId());
    userResponse.setEmail(staff.getAccount().getEmail());
    userResponse.setStatus(staff.getAccount().isActivated());
    return userResponse;
  }

  @Override
  public void delete(String accountId) {
    Staff staff = staffRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    Account account = staff.getAccount();
    staff.setStatus(false);
    account.setActivated(false);
  }

  @Override
  public UserResponse get(String userId) {
    Staff staff = staffRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    UserResponse userResponse = userMapper.fromStafftoUserResponse(staff);
    userResponse.setAccountId(staff.getAccount().getAccountId());
    userResponse.setCreatedAt(staff.getAccount().getCreatedAt());
    userResponse.setEmail(staff.getAccount().getEmail());
    userResponse.setStatus(staff.getAccount().isActivated());
    userResponse.setRoles(staff.getAccount().getRoles());
    return userResponse;
  }

  @Override
  public List<UserResponse> getAll() {
    return staffRepository.findAll().stream().map(userMapper::fromStafftoUserResponse).toList();
  }

}
