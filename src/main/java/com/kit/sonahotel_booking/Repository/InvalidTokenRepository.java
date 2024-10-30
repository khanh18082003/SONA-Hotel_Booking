package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.InvalidToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}
