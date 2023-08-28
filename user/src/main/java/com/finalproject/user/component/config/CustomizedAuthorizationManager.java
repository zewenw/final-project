package com.finalproject.user.component.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.finalproject.user.constant.RedisConstants;
import com.finalproject.user.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CustomizedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedAuthorizationManager.class);

    @Autowired
    private RoleService roleService;
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


    //Authorization
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext authorizationContext) {
        // get current request path
        String uri = authorizationContext.getRequest().getRequestURI();
        logger.info("current request path: {}", uri);

        // 获取可访问当前路径的所有角色
        Object roles = redisTemplate.opsForHash().get(RedisConstants.PERMISSION_ROLE.toString(), uri);
        if(roles == null){
            return new AuthorizationDecision(true);
        }
        List<String> authorities = roles == null ? null : Convert.toList(String.class, roles);
        logger.info("current path authorities: {}", JSONUtil.toJsonStr(authorities));
        JwtAuthenticationToken token = (JwtAuthenticationToken) authenticationSupplier.get();
        boolean authenticated = token.isAuthenticated();
        if (authenticated) {
            assert authorities != null;
            String username = token.getName();
            List<String> roleCodes = roleService.getRoleCodeByUsername(username);
            return new AuthorizationDecision(roleCodes.stream()
                    .anyMatch(authorities::contains));
        }
        return new AuthorizationDecision(false);
    }
}
