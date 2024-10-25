package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  boolean existsByRoleName(String roleName);

  @Query("SELECT r FROM Role r WHERE r.roleName IN :roleName")
  List<Role> findAllByRoleName(List<String> roleName);
}
