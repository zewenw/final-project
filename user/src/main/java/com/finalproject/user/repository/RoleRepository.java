package com.finalproject.user.repository;

import com.finalproject.user.entity.Role;
import com.finalproject.user.entity.view.RoleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleCode(String roleCode);

    Role findByRoleName(String roleName);

    @Query(value = "select r.id as id, r.role_code, r.role_name, r.role_description, r.date_created," +
            " r.last_updated from role r left join authorities a on r.role_code = a.authority where a.username = :username"
            , nativeQuery = true)
    List<Role> findOwnedRolesByUsername(@Param("username") String username);

    @Query(value = "select r2.id , r2.role_code as roleCode , r2.role_name as roleName , r2.role_description as roleDescription from role r2 where r2.role_code not in (\n" +
            "select r.role_code from role r left join authorities a on r.role_code = a.authority where a.username = :username)"
            , nativeQuery = true)
    List<RoleView> findLackedRolesByUsername(@Param("username") String username);
}
