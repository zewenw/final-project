package com.finalproject.user.controller;

import com.finalproject.user.component.exception.BusinessException;
import com.finalproject.user.dto.request.PermissionRequest;
import com.finalproject.user.dto.response.PermissionResponse;
import com.finalproject.user.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("/permission")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "add new permission")
    public ResponseEntity<PermissionResponse> addPermission(@RequestBody @Valid PermissionRequest permissionRequest) {
        String permissionCode = permissionRequest.getPermissionCode();
        String permissionType = permissionRequest.getPermissionType();
        if (permissionService.checkDuplicatePermission(permissionCode, permissionType)) {
            throw new BusinessException("DUPLICATE PERMISSIONCODE");
        }
        return ResponseEntity.ok().body(permissionService.addPermission(permissionRequest));
    }

    @GetMapping("/permissions")
    @Operation(description = "get all permissions")
    public ResponseEntity<List<PermissionResponse>> getAllPermissions() {
        return ResponseEntity.ok().body(permissionService.getAllPermissions());
    }

    @GetMapping("/permissions/{roleId}")
    @Operation(description = "get permissions by role id")
    public ResponseEntity<List<PermissionResponse>> getPermissionsByRoleId(@PathVariable String roleId) {
        return ResponseEntity.ok().body(permissionService.getPermissionByRoleId(Long.valueOf(roleId)));
    }

    @GetMapping("/permission")
    @Operation(description = "get permission by permission code")
    public ResponseEntity<PermissionResponse> getPermission(@RequestParam @NotNull String permissionCode) {
        return ResponseEntity.ok().body(permissionService.getPermission(permissionCode));
    }

    @GetMapping("/permission/page")
    @Operation(description = "get permission by page")
    public ResponseEntity<Page<PermissionResponse>> getPermissions(PermissionRequest permissionRequest) {
        return ResponseEntity.ok().body(permissionService.getPermissionByPage(permissionRequest));
    }

    @PutMapping("/permission")
    @Operation(description = "update permission")
    public ResponseEntity<PermissionResponse> updatePermission(@RequestBody @Valid PermissionRequest permissionRequest) {
        return ResponseEntity.ok().body(permissionService.updatePermission(permissionRequest));
    }

    @PutMapping("/permissions/unbind/{roleId}/{permissionId}")
    @Operation(description = "unbind Permission With Role")
    public ResponseEntity<Boolean> unbindPermissionWithRole(@PathVariable String roleId, @PathVariable String permissionId) {
        return ResponseEntity.ok().body(permissionService.unbindPermissionWithRole(roleId, permissionId));
    }

    @PutMapping("/permissions/bind/{roleId}/{permissionId}")
    @Operation(description = "bind Permission With Role")
    public ResponseEntity<Boolean> bindPermissionWithRole(@PathVariable String roleId, @PathVariable String permissionId) {
        return ResponseEntity.ok().body(permissionService.bindPermissionWithRole(roleId, permissionId));
    }

    @DeleteMapping("/permission/{id}")
    @Operation(description = "delete permission by id")
    public ResponseEntity<String> deletePermission(@PathVariable long id) {
        //TODO check whether related with a role
        permissionService.deletePermissionById(id);
        return ResponseEntity.ok().build();
    }

}
