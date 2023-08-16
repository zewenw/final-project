package com.finalproject.user.service;


import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;

public interface RoleService {

    boolean checkDuplicateRoleCode(String roleCode);

    boolean checkDuplicateRoleName(String roleName);

    RoleResponse addRole(RoleRequest roleRequest);

    RoleResponse getRole(String rolecode);

    RoleResponse updateUser(RoleRequest roleRequest);

    void deleteRoleById(long id);
}
