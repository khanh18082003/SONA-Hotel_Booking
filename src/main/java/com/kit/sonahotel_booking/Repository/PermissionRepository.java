package com.kit.sonahotel_booking.Repository;

import com.kit.sonahotel_booking.Entity.Permission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

  boolean existsByPermissionName(String permissionName);

  @Query("SELECT p FROM Permission p WHERE p.permissionName IN :permissionNames")
  List<Permission> findAllByPermissionName(List<String> permissionNames);
}
