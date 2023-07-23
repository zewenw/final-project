package com.finalproject.demomodule.repository.many2many.bidirection;

import com.finalproject.demomodule.entity.many2many.bidirection.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bidirectionalUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
