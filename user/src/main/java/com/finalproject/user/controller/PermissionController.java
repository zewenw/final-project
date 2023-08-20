package com.finalproject.user.controller;

import com.finalproject.user.dto.request.PermissionRequest;
import com.finalproject.user.dto.response.PermissionResponse;
import com.finalproject.user.component.exception.BusinessException;
import com.finalproject.user.service.PermissionService;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("/permission")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "add new permission")
    public ResponseEntity<PermissionResponse> addPermission(@RequestBody @Valid PermissionRequest permissionRequest){
        String permissionCode = permissionRequest.getPermissionCode();
        String permissionType = permissionRequest.getPermissionType();
        if(permissionService.checkDuplicatePermission(permissionCode, permissionType)){
            throw new BusinessException("DUPLICATE PERMISSIONCODE");
        }
        return ResponseEntity.ok().body(permissionService.addPermission(permissionRequest));
    }

    @GetMapping("/permission/{rolecode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get permission by permission code")
    public ResponseEntity<PermissionResponse> getPermission(@PathVariable @NotNull String permissionCode){
        return ResponseEntity.ok().body(permissionService.getPermission(permissionCode));
    }

    @PutMapping("/permission")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "update permission")
    public ResponseEntity<PermissionResponse> updatePermission(@RequestBody @Valid PermissionRequest permissionRequest){
        return ResponseEntity.ok().body(permissionService.updatePermission(permissionRequest));
    }

    @DeleteMapping("/permission/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "delete permission by id")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        permissionService.deletePermissionById(id);
        return  ResponseEntity.ok().build();
    }

}
