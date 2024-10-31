package com.kit.sonahotel_booking.Service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.kit.sonahotel_booking.Entity.Account;
import com.kit.sonahotel_booking.Entity.InvalidToken;
import com.kit.sonahotel_booking.Exception.AppException;
import com.kit.sonahotel_booking.Exception.ErrorCode;
import com.kit.sonahotel_booking.Repository.AccountRepository;
import com.kit.sonahotel_booking.Repository.InvalidTokenRepository;
import com.kit.sonahotel_booking.Service.IAuthenticationService;
import com.kit.sonahotel_booking.dto.request.AuthenticationRequest;
import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.IntrospectionRequest;
import com.kit.sonahotel_booking.dto.request.InvalidTokenRequest;
import com.kit.sonahotel_booking.dto.response.AuthenticationResponse;
import com.kit.sonahotel_booking.dto.response.IntrospectionResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements IAuthenticationService {

  AccountRepository accountRepository;
  PasswordEncoder passwordEncoder;
  InvalidTokenRepository invalidTokenRepository;

  @NonFinal
  @Value("${jwt.signed_key}")
  String SIGNED_KEY;

  @Override
  public AuthenticationResponse authenticated(GeneralDtoRequest request) {
    AuthenticationRequest authenticationRequest = (AuthenticationRequest) request;
    Account account = accountRepository.findByEmail(authenticationRequest.getEmail())
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    if (!passwordEncoder.matches(authenticationRequest.getPassword(), account.getPassword())) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    return AuthenticationResponse.builder()
        .token(generateToken(account))
        .build();
  }

  @Override
  public IntrospectionResponse introspect(IntrospectionRequest request)
      throws JOSEException, ParseException {
    boolean isValid = true;
    try {
      verifyToken(request.getToken());
    } catch (AppException e) {
      isValid = false;
    }
    return IntrospectionResponse.builder()
        .isAuthenticated(isValid)
        .build();
  }

  @Override
  public void logout(InvalidTokenRequest request) throws ParseException, JOSEException {
    var signedJWT = verifyToken(request.getToken());
    String jid = signedJWT.getJWTClaimsSet().getJWTID();
    Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

    invalidTokenRepository.save(
        InvalidToken.builder()
            .id(jid)
            .expiredAt(expirationTime)
            .build());
  }

  private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
    JWSVerifier jwsVerifier = new MACVerifier(SIGNED_KEY);
    SignedJWT signedJWT = SignedJWT.parse(token);

    Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

    if (!(signedJWT.verify(jwsVerifier) || expirationTime.after(new Date()))) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    if (invalidTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    return signedJWT;
  }

  private String generateToken(Account account) {
    // Jws header
    JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

    // JWT Claimset
    JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
        .subject(account.getEmail())
        .issuer("sona-booking.com")
        .issueTime(new Date())
        .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
        .jwtID(UUID.randomUUID().toString())
        .claim("scope", buildingScope(account))
        .build();
    // Payload
    Payload payload = new Payload(jwtClaimsSet.toJSONObject());

    JWSObject jwsObject = new JWSObject(jwsHeader, payload);
    try {
      jwsObject.sign(new MACSigner(SIGNED_KEY));
      return jwsObject.serialize();
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
  }

  private String buildingScope(Account account) {
    var roles = account.getRoles();
    StringJoiner joiner = new StringJoiner(" ");
    if (CollectionUtils.isEmpty(roles)) {
      return joiner.toString();
    }
    roles.forEach(role -> {
      joiner.add(role.getRoleName());
      var permissions = role.getPermissions();
      if (!CollectionUtils.isEmpty(permissions)) {
        permissions.forEach(permission -> joiner.add(permission.getPermissionName()));
      }
    });
    return joiner.toString();
  }
}
