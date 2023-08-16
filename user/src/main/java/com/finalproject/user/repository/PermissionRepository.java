package com.finalproject.user.repository;

import com.finalproject.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByPermissionCode(String permissionCode);
}
