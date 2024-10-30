package com.kit.sonahotel_booking.Controller;

import com.kit.sonahotel_booking.Service.IAuthenticationService;
import com.kit.sonahotel_booking.dto.request.AuthenticationRequest;
import com.kit.sonahotel_booking.dto.request.IntrospectionRequest;
import com.kit.sonahotel_booking.dto.request.InvalidTokenRequest;
import com.kit.sonahotel_booking.dto.response.ApiResponse;
import com.kit.sonahotel_booking.dto.response.AuthenticationResponse;
import com.kit.sonahotel_booking.dto.response.IntrospectionResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import java.text.ParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationController {

  IAuthenticationService authenticationService;

  @PostMapping("/login")
  ApiResponse<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
    return ApiResponse.<AuthenticationResponse>builder()
        .code(1009)
        .message("Login successful")
        .data(authenticationService.authenticated(request))
        .build();
  }

  @PostMapping("/introspect")
  ApiResponse<IntrospectionResponse> introspect(@RequestBody IntrospectionRequest request)
      throws ParseException, JOSEException {
    IntrospectionResponse response = authenticationService.introspect(request);
    return ApiResponse.<IntrospectionResponse>builder()
        .code(1009)
        .data(response)
        .message(response.isAuthenticated() ? "Token is valid" : "Token is invalid")
        .build();
  }

  @PostMapping("/logout")
  ApiResponse<Void> logout(@RequestBody InvalidTokenRequest request)
      throws ParseException, JOSEException {
    authenticationService.logout(request);
    return ApiResponse.<Void>builder()
        .code(1012)
        .message("Logout successful")
        .build();
  }
}
