package com.finalproject.user.service;


import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    boolean checkDuplicateRoleCode(String roleCode);

    boolean checkDuplicateRoleName(String roleName);

    RoleResponse addRole(RoleRequest roleRequest);

    RoleResponse getRole(String rolecode);

    RoleResponse udpateRole(RoleRequest roleRequest);

    void deleteRoleById(long id);

    List<RoleResponse> getAll();

    Boolean bindUserWithRole(long roleId, String username);
}
