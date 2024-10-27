package com.kit.sonahotel_booking.Service.impl;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.Customer;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Mapper.AccountMapper;
import com.kit.sonahotel_booking.Mapper.UserMapper;
import com.kit.sonahotel_booking.Repository.AccountRepository;
import com.kit.sonahotel_booking.Repository.CustomerRepository;
import com.kit.sonahotel_booking.Repository.RoleRepository;
import com.kit.sonahotel_booking.Service.ICustomerService;
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
public class CustomerServiceImpl implements ICustomerService {

  CustomerRepository customerRepository;
  AccountRepository accountRepository;
  PasswordEncoder passwordEncoder;
  UserMapper userMapper;
  AccountMapper accountMapper;
  RoleRepository roleRepository;

  public UserResponse save(GeneralDtoRequest request) {
    UserCreationRequest userCreationRequest = (UserCreationRequest) request;
    if (accountRepository.existsByEmail(userCreationRequest.getEmail())) {
      throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
    if (customerRepository.existsByPhoneNumber(userCreationRequest.getPhoneNumber())) {
      throw new AppException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

    Account account = accountMapper.toAccount(userCreationRequest);
    account.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
    account.setActivated(true);
    account.setCreatedAt(LocalDate.now());
    var roles = roleRepository.findAllByRoleName(List.of(Roles.CUSTOMER.name()));
    account.setRoles(new HashSet<>(roles));
    accountRepository.save(account);

    Customer customer = userMapper.toCustomer(userCreationRequest);
    customer.setAccount(account);
    UserResponse userResponse = userMapper.fromCustomertoUserResponse(
        customerRepository.save(customer));
    userResponse.setAccountId(account.getAccountId());
    userResponse.setEmail(account.getEmail());
    userResponse.setStatus(account.isActivated());
    userResponse.setCreatedAt(account.getCreatedAt());
    userResponse.setRoles(account.getRoles());
    return userResponse;
  }

  public UserResponse update(GeneralDtoRequest request, String accountId) {
    UserUpdateRequest userUpdateRequest = (UserUpdateRequest) request;

    Customer customer = customerRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AppException((ErrorCode.USER_NOT_FOUND)));

    userMapper.updateCustomer(userUpdateRequest, customer);
    customerRepository.save(customer);

    UserResponse userResponse = userMapper.fromCustomertoUserResponse(customer);
    userResponse.setAccountId(customer.getAccount().getAccountId());
    userResponse.setEmail(customer.getAccount().getEmail());
    userResponse.setStatus(customer.getAccount().isActivated());
    return userResponse;
  }

  @Override
  public void delete(String accountId) {
    Customer customer = customerRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    Account account = customer.getAccount();
    account.setActivated(false);
  }

  @Override
  public UserResponse get(String accountId) {
    Customer customer = customerRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    UserResponse userResponse = userMapper.fromCustomertoUserResponse(customer);
    userResponse.setAccountId(customer.getAccount().getAccountId());
    userResponse.setEmail(customer.getAccount().getEmail());
    userResponse.setStatus(customer.getAccount().isActivated());
    userResponse.setRoles(customer.getAccount().getRoles());
    userResponse.setCreatedAt(customer.getAccount().getCreatedAt());
    return userResponse;
  }

  @Override
  public List<UserResponse> getAll() {
    return customerRepository.findAll().stream().map(
        customer -> {
          UserResponse userResponse = userMapper.fromCustomertoUserResponse(customer);
          userResponse.setAccountId(customer.getAccount().getAccountId());
          userResponse.setEmail(customer.getAccount().getEmail());
          userResponse.setStatus(customer.getAccount().isActivated());
          userResponse.setCreatedAt(customer.getAccount().getCreatedAt());
          userResponse.setRoles(customer.getAccount().getRoles());
          return userResponse;
        }
    ).toList();
  }
}
