package com.finalproject.user.component.task;

import com.finalproject.user.constant.RedisConstants;
import com.finalproject.user.entity.Permission;
import com.finalproject.user.entity.Role;
import com.finalproject.user.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoadPermissionToRedis implements ApplicationRunner {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadDataToRedis();
    }

    public void loadDataToRedis() {
        log.info("load authorization data to redis");
        List<Permission> permissions = permissionRepository.findAll();
        for (Permission permission : permissions) {
            Set<Role> roles = permission.getRoles();
            if (roles != null && !roles.isEmpty()) {
                Set<String> roleCodes = roles.stream().map(Role::getRoleCode).collect(Collectors.toSet());
                Object value = redisTemplate.opsForHash().get(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode());
                if (value != null && value instanceof List) {
                    Set<String> roleSet = (Set<String>) value;
                    roleSet.addAll(roleCodes);
                    redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), roleSet);
                } else {
                    redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), roleCodes);
                }
            }else {
                redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), new HashSet<>());
            }
        }
    }
}
