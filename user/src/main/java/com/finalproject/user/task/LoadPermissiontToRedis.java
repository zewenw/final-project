package com.finalproject.user.task;

import com.finalproject.user.constant.RedisConstants;
import com.finalproject.user.entity.Permission;
import com.finalproject.user.entity.Role;
import com.finalproject.user.repository.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoadPermissiontToRedis implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(LoadPermissiontToRedis.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("=============start up and load data to redis==========================");
        List<Permission> permissions = permissionRepository.findAll();
        for (Permission permission : permissions) {
            Set<Role> roles = permission.getRoles();
            List<String> roleCodes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
            redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), roleCodes);
        }
    }
}
