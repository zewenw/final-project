package com.finalproject.photo.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.finalproject.photo.constant.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CustomizedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedAuthorizationManager.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    private String organizeUri(String originalUrl) {
//        String[] originalParts = StringUtils.tokenizeToStringArray(originalUrl, "/");
//
//        StringBuilder newPath = new StringBuilder("/");
//        for (int i = 0; i < originalParts.length; i++) {
//            if (i >= 1) {
//                // only append slash if this is the second part or greater
//                if (newPath.length() > 1) {
//                    newPath.append('/');
//                }
//                newPath.append(originalParts[i]);
//            }
//        }
//        return newPath.toString();
//    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext authorizationContext) {
        // get current request path
        String uri = authorizationContext.getRequest().getRequestURI();
        logger.info("current request path: {}", uri);

        // 获取可访问当前路径的所有角色
        Object roles = redisTemplate.opsForHash().get(RedisConstants.PERMISSION_ROLE.toString(), uri);
        List<String> authorities = roles == null ? null : Convert.toList(String.class, roles);
        logger.info("current path authorities: {}", JSONUtil.toJsonStr(authorities));
        Authentication authentication = authenticationSupplier.get();
        boolean authenticated = authentication.isAuthenticated();
        if (authenticated) {
            assert authorities != null;
            return new AuthorizationDecision(authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(authorities::contains));
        }
        return new AuthorizationDecision(false);
    }
}
