package com.finalproject.user.service;


import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    boolean checkDuplicateRoleCode(String roleCode);

    boolean checkDuplicateRoleName(String roleName);

    RoleResponse addRole(RoleRequest roleRequest);

    RoleResponse getRole(String rolecode);

    RoleResponse updateRole(RoleRequest roleRequest);

    void deleteRoleById(long id);

    List<RoleResponse> getAll();

    Boolean bindUserWithRole(long roleId, String username);

    Page<RoleResponse> getRoleByPage(RoleRequest roleRequest);

    List<RoleResponse> getOwnedRoleByUsername(String username);

    List<RoleResponse> getLackedRoleByUsername(String username);

    Boolean unbindUserWithRole(long roleId, String username);

    List<String> getRoleCodeByUsername(String username);
}



