package com.kit.sonahotel_booking.Configuration;

import com.kit.sonahotel_booking.Service.IAuthenticationService;
import com.kit.sonahotel_booking.dto.request.IntrospectionRequest;
import com.nimbusds.jose.JOSEException;
import java.text.ParseException;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {

  @Autowired
  IAuthenticationService authenticationService;

  NimbusJwtDecoder jwtDecoder = null;

  @Value("${jwt.signed_key}")
  private String SIGNED_KEY;

  @Override
  public Jwt decode(String token) {
    try {
      var response = authenticationService.introspect(
          IntrospectionRequest.builder()
              .token(token)
              .build()
      );
      if (!response.isAuthenticated()) {
        throw new JwtException("Invalid token");
      }
    } catch (JOSEException | ParseException e) {
      throw new JwtException("Invalid token", e);
    }

    if (Objects.isNull(jwtDecoder)) {
      SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNED_KEY.getBytes(), "HS512");
      jwtDecoder = NimbusJwtDecoder
          .withSecretKey(secretKeySpec)
          .macAlgorithm(MacAlgorithm.HS512)
          .build();
    }

    return jwtDecoder.decode(token);
  }

}
