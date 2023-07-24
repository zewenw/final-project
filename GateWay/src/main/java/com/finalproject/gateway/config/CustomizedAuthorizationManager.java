package com.finalproject.gateway.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.finalproject.gateway.constant.RedisConstants;
import com.finalproject.gateway.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

//@Component
public class CustomizedAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedAuthorizationManager.class);

//    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取当前访问路径
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        // 获取可访问当前路径的所有角色
        Object roles = redisTemplate.opsForHash().get(RedisConstants.RESOURCE_ROLES_PATH.toString(), uri.getPath());
        logger.info("current request path: {}", uri.getPath());
        List<String> authorities = roles == null ? roleRepository.selectUrlRole(uri.getPath()) : Convert.toList(String.class, roles);
        logger.info("current path authorities: {}", JSONUtil.toJsonStr(authorities));

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
