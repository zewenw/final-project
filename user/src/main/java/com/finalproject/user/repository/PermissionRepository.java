package com.finalproject.user.repository;

import com.finalproject.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
}
