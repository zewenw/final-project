package com.finalproject.demomodule.repository.many2many.unidirection;

import com.finalproject.demomodule.entity.many2many.unidirection.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("unidirectionalUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
