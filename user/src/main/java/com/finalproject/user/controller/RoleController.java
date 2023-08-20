package com.finalproject.user.controller;

import com.finalproject.user.component.exception.BusinessException;
import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import com.finalproject.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "add new role")
    public ResponseEntity<RoleResponse> addRole(@RequestBody @Valid RoleRequest roleRequest) {
        String roleCode = roleRequest.getRoleCode();
        if (roleService.checkDuplicateRoleCode(roleCode)) {
            throw new BusinessException("DUPLICATE ROLENAME");
        }
        String roleName = roleRequest.getRoleName();
        if (roleService.checkDuplicateRoleName(roleName)) {
            throw new BusinessException("DUPLICATE ROLENAME");
        }
        return ResponseEntity.ok().body(roleService.addRole(roleRequest));
    }

    @GetMapping("/role/{rolecode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by role code")
    public ResponseEntity<RoleResponse> getRole(@PathVariable @NotNull String rolecode) {
        return ResponseEntity.ok().body(roleService.getRole(rolecode));
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by role code")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @PutMapping("/role/{roleId}/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "bind a new role with the user")
    public ResponseEntity<Boolean> updateRole(@PathVariable long roleId, @PathVariable String username) {
        return ResponseEntity.ok().body(roleService.bindUserWithRole(roleId, username));
    }

    @DeleteMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "delete role by id")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }


}
