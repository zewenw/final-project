package com.finalproject.demomodule.repository.many2many.bidirection;

import com.finalproject.demomodule.entity.many2many.bidirection.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bidirectionalRoleRepository")
public interface RoleRepository  extends JpaRepository<Role, Long> {
}
