package com.finalproject.demomodule.repository.many2many.unidirection;

import com.finalproject.demomodule.entity.many2many.unidirection.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("unidirectionalRoleRepository")
public interface RoleRepository  extends JpaRepository<Role, Long> {
}
