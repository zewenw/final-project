package com.finalproject.user.repository;

import com.finalproject.user.entity.Permission;
import com.finalproject.user.entity.Role;
import com.finalproject.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

//@DataJpaTest
//@AutoConfigureTestDatabase
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){
        Permission photoPermission = Permission.builder().permissionCode("/photo/sayHello").permissionName("/photo/sayHello").permissionType("path").permissionDescription("user module").build();
        Permission userPermission = Permission.builder().permissionCode("/user/sayHello").permissionName("/user/sayHello").permissionType("path").permissionDescription("user module").build();

        Set<Permission> photoPermissionSet = Set.of(photoPermission);
        Set<Permission> userPermissionSet = Set.of(userPermission);

        Role photoAdmin = Role.builder().roleCode("PhotoAdmin").roleName("Photo Administrator").roleDescription("root user of photo module").permissions(photoPermissionSet).build();
        Role userAdmin = Role.builder().roleCode("UserAdmin").roleName("User Administrator").roleDescription("root user of user module").permissions(userPermissionSet).build();

        Set<Role> photoAdminRoleSet = Set.of(photoAdmin);
        Set<Role> userAdminRoleSet = Set.of(userAdmin);

        User john = User.builder().username("john").email("john@email.com").password("{noop}123").enabled(true).roles(photoAdminRoleSet).build();
        User mary = User.builder().username("mary").email("mary@email.com").password("{noop}123").enabled(true).roles(userAdminRoleSet).build();
        User susan = User.builder().username("susan").email("susan@email.com").password("{noop}123").enabled(true).build();

        userRepository.save(john);
        userRepository.save(mary);
        userRepository.save(susan);
    }
}
