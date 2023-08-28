package com.finalproject.user.repository;

import com.finalproject.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByPermissionCode(String permissionCode);

    @Query(value = "select * from permissions p left join role_permission rp on p.id = rp.permission_id where rp.role_id = :roleId"
    , nativeQuery = true)
    List<Permission> findByRoleId(@Param("roleId") Long roleId);
}
