package com.finalproject.user.service;


import com.finalproject.user.dto.request.PermissionRequest;
import com.finalproject.user.dto.response.PermissionResponse;

public interface PermissionService {

    boolean checkDuplicatePermission(String permissionCode, String permissionType);

    PermissionResponse addPermission(PermissionRequest permissionRequest);

    PermissionResponse getPermission(String permissionCode);

    PermissionResponse updatePermission(PermissionRequest permissionRequest);

    void deletePermissionById(long id);
}
