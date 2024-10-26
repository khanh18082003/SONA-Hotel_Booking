package com.kit.sonahotel_booking.Configuration;

import com.kit.sonahotel_booking.Service.IApplicationInitService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

  IApplicationInitService applicationInitService;

  @Bean
  ApplicationRunner applicationRunner() {
    return args -> {
      log.info("Application started");
      applicationInitService.init();
    };
  }
}
