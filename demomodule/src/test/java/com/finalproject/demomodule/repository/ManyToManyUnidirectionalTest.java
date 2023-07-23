package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.many2many.unidirection.Role;
import com.finalproject.demomodule.entity.many2many.unidirection.User;
import com.finalproject.demomodule.repository.many2many.unidirection.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUnidirectionalTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser(){
        User user = new User();
        user.setFirstName("ramesh");
        user.setLastName("fadatare");
        user.setEmail("ramesh@gmail.com");
        user.setPassword("secrete");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");

        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        user.getRoles().add(admin);
        user.getRoles().add(customer);

        userRepository.save(user);
    }

    @Test
    void updateUser(){
        User user = userRepository.findById(1L).get();
        user.setFirstName("ram");
        user.setEmail("ram@gmail.com");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        user.getRoles().add(roleUser);

        userRepository.save(user);
    }

    @Test
    void fetchUser(){
        User user = userRepository.findById(1L).get();
        System.out.println(user.getEmail());
        user.getRoles().forEach((r) -> {
            System.out.println(r.getName());
        });
    }

    @Test
    void deleteUser(){
        userRepository.deleteById(1L);
    }
}
