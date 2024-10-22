package com.kit.sonahotel_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SonaHotelBookingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SonaHotelBookingApplication.class, args);
  }

}
