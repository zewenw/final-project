package com.finalproject.user.controller;

import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import com.finalproject.user.exception.UserException;
import com.finalproject.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "add new role")
    public ResponseEntity<RoleResponse> addRole(@RequestBody @Valid RoleRequest roleRequest){
        String roleCode = roleRequest.getRoleCode();
        if(roleService.checkDuplicateRoleCode(roleCode)){
            throw new UserException("DUPLICATE ROLENAME");
        }
        String roleName = roleRequest.getRoleName();
        if(roleService.checkDuplicateRoleName(roleName)){
            throw new UserException("DUPLICATE ROLENAME");
        }
        return ResponseEntity.ok().body(roleService.addRole(roleRequest));
    }

    @GetMapping("/user/{rolecode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get role by role code")
    public ResponseEntity<RoleResponse> getRole(@PathVariable @NotNull String rolecode){
        return ResponseEntity.ok().body(roleService.getRole(rolecode));
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "update role")
    public ResponseEntity<RoleResponse> updateUser(@RequestBody @Valid RoleRequest roleRequest){
        return ResponseEntity.ok().body(roleService.updateUser(roleRequest));
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "delete role id")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        roleService.deleteRoleById(id);
        return  ResponseEntity.ok().build();
    }


}
