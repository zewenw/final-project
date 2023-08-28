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
import org.springframework.data.domain.Page;
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
        log.info("com.finalproject.user.controller.RoleController.addRole, param: {}", roleRequest);
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

    @GetMapping("/role/{roleCode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by role code")
    public ResponseEntity<RoleResponse> getRole(@PathVariable @NotNull String roleCode) {
        log.info("com.finalproject.user.controller.RoleController.getRole, param: {}", roleCode);
        return ResponseEntity.ok().body(roleService.getRole(roleCode));
    }

    @GetMapping("/roles/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by username")
    public ResponseEntity<List<RoleResponse>> getOwnedRoleByUsername(@PathVariable @NotNull String username) {
        log.info("com.finalproject.user.controller.RoleController.getOwnedRoleByUsername, param: {}", username);
        return ResponseEntity.ok().body(roleService.getOwnedRoleByUsername(username));
    }

    @GetMapping("/roles/lacked/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get the current user does not own the role in")
    public ResponseEntity<List<RoleResponse>> getLackedRoleByUsername(@PathVariable @NotNull String username) {
        log.info("com.finalproject.user.controller.RoleController.getLackedRoleByUsername, param: {}", username);
        return ResponseEntity.ok().body(roleService.getLackedRoleByUsername(username));
    }

    @GetMapping("/role/page")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by page")
    public ResponseEntity<Page<RoleResponse>> getRoleByPage(@Valid RoleRequest roleRequest) {
        log.info("com.finalproject.user.controller.RoleController.getRoleByPage, param: {}", roleRequest);
        return ResponseEntity.ok().body(roleService.getRoleByPage(roleRequest));
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by role code")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        log.info("com.finalproject.user.controller.RoleController.getAllRoles");
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @PutMapping("/role/bind/{roleId}/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "bind a new role with the user")
    public ResponseEntity<Boolean> bindUserWithRole(@PathVariable long roleId, @PathVariable String username) {
        log.info("com.finalproject.user.controller.RoleController.bindUserWithRole, roleId: {}, username: {}", roleId, username);
        return ResponseEntity.ok().body(roleService.bindUserWithRole(roleId, username));
    }

    @PutMapping("/role/unbind/{roleId}/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "bind a new role with the user")
    public ResponseEntity<Boolean> unbindUserWithRole(@PathVariable long roleId, @PathVariable String username) {
        log.info("com.finalproject.user.controller.RoleController.unbindUserWithRole, roleId: {}, username: {}", roleId, username);
        return ResponseEntity.ok().body(roleService.unbindUserWithRole(roleId, username));
    }

    @PutMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "bind a new role with the user")
    public ResponseEntity<RoleResponse> updateRole(@RequestBody @Valid RoleRequest roleRequest) {
        log.info("com.finalproject.user.controller.RoleController.updateRole, param: {}", roleRequest);
        return ResponseEntity.ok().body(roleService.udpateRole(roleRequest));
    }

    @DeleteMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "delete role by id")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        log.info("com.finalproject.user.controller.RoleController.deleteRole, param: {}", id);
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }


}
