package com.finalproject.user.component.task;

import com.finalproject.user.constant.RedisConstants;
import com.finalproject.user.entity.Permission;
import com.finalproject.user.entity.Role;
import com.finalproject.user.repository.PermissionRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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
                List<String> roleCodes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
                Object value = redisTemplate.opsForHash().get(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode());
                if (value != null && value instanceof List) {
                    List<String> roleList = (List<String>) value;
                    roleList.addAll(roleCodes);
                    redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), roleList);
                } else {
                    redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), roleCodes);
                }
            }else {
                redisTemplate.opsForHash().put(RedisConstants.PERMISSION_ROLE.name(), permission.getPermissionCode(), Lists.newArrayList());
            }
        }
    }
}
