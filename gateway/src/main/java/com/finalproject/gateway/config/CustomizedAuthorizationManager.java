package com.finalproject.gateway.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.finalproject.gateway.constant.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

//@Component
public class CustomizedAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedAuthorizationManager.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取当前访问路径
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        String requestUrl = organizeUri(uri.getPath());

        // 获取可访问当前路径的所有角色
        Object roles = redisTemplate.opsForHash().get(RedisConstants.PERMISSION_ROLE.toString(), requestUrl);
        logger.info("current request path: {}", uri.getPath());
        List<String> authorities = roles == null ? null : Convert.toList(String.class, roles);
        logger.info("current path authorities: {}", JSONUtil.toJsonStr(authorities));
//        return mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authorities::contains)
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
        return mono
                .filter(Authentication::isAuthenticated)
                .doOnNext(authentication -> logger.info("=======================Authenticated: " + authentication))
                .flatMapIterable(Authentication::getAuthorities)
                .doOnNext(authority -> logger.info("=======================Authority: " + authority.getAuthority()))
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .doOnNext(result -> logger.info("=======================Authorization Result: " + result))
                .map(AuthorizationDecision::new)
                .doOnNext(decision -> logger.info("=======================Final Decision: " + decision.isGranted()))
                .defaultIfEmpty(new AuthorizationDecision(false))
                .doOnNext(defaultDecision -> logger.info("=======================Default Decision: " + defaultDecision.isGranted()));
    }

    private String organizeUri(String originalUrl){
        String[] originalParts = StringUtils.tokenizeToStringArray(originalUrl, "/");

        StringBuilder newPath = new StringBuilder("/");
        for (int i = 0; i < originalParts.length; i++) {
            if (i >= 1) {
                // only append slash if this is the second part or greater
                if (newPath.length() > 1) {
                    newPath.append('/');
                }
                newPath.append(originalParts[i]);
            }
        }
        return newPath.toString();
    }

}
