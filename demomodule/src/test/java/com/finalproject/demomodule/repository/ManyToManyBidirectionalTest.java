package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.many2many.bidirection.Role;
import com.finalproject.demomodule.entity.many2many.bidirection.User;
import com.finalproject.demomodule.repository.many2many.bidirection.RoleRepository;
import com.finalproject.demomodule.repository.many2many.bidirection.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ManyToManyBidirectionalTest {


    @Resource(name = "bidirectionalRoleRepository")
    private RoleRepository roleRepository;

    @Resource(name = "bidirectionalUserRepository")
    private UserRepository userRepository;

    @Test
    void saveRole(){
        User user = new User();
        user.setFirstName("ramesh");
        user.setLastName("fadatare");
        user.setEmail("ramesh@gmail.com");
        user.setPassword("secrete");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        roleAdmin.getUsers().add(user);
        roleAdmin.getUsers().add(admin);

        user.getRoles().add(roleAdmin);
        user.getRoles().add(roleUser);
        admin.getRoles().add(roleAdmin);

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
    }

    @Test
    void fetchRole(){
        List<Role> roles = roleRepository.findAll();
        roles.forEach((r) ->{
            System.out.println(r.getName());
            r.getUsers().forEach((u) ->{
                System.out.println(u.getFirstName());
            });
        });
    }

    @Test
    void deleteUser(){
        userRepository.deleteById(4L);
        userRepository.deleteById(5L);
    }

    @Test
    void deleteUserWithoutRoles(){
        Optional<User> userOp = userRepository.findById(11L);
        Optional<Role> roleOp = roleRepository.findById(12L);
        if (userOp.isPresent() && roleOp.isPresent()) {
            // Remove the entities from their respective associations
            User user = userOp.get();
            Role role = roleOp.get();
            user.getRoles().remove(role);
//            role.getUsers().remove(user);

            // Save the changes to the database
            userRepository.save(user);
//            roleRepository.save(role);
        }
    }
}
