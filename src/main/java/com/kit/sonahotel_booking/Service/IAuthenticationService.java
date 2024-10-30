package com.kit.sonahotel_booking.Service;

import com.kit.sonahotel_booking.dto.request.GeneralDtoRequest;
import com.kit.sonahotel_booking.dto.request.IntrospectionRequest;
import com.kit.sonahotel_booking.dto.request.InvalidTokenRequest;
import com.kit.sonahotel_booking.dto.response.AuthenticationResponse;
import com.kit.sonahotel_booking.dto.response.IntrospectionResponse;
import com.nimbusds.jose.JOSEException;
import java.text.ParseException;

public interface IAuthenticationService extends IGeneralService<AuthenticationResponse, String> {

  AuthenticationResponse authenticated(GeneralDtoRequest request);

  IntrospectionResponse introspect(IntrospectionRequest request)
      throws JOSEException, ParseException;

  void logout(InvalidTokenRequest request) throws ParseException, JOSEException;
}
