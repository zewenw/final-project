package com.finalproject.user.repository;

import com.finalproject.user.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PermissionTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void findOne(){
        Permission permission = Permission.builder().id(1L).build();
        Example<Permission> permissionExample = Example.of(permission);
        Optional<Permission> one = permissionRepository.findOne(permissionExample);
        System.out.println(one);

    }

    @Test
    public void findAll(){
        List<Permission> permissions = permissionRepository.findAll();
        permissions.stream().forEach(p -> System.out.println(p));

    }
}
