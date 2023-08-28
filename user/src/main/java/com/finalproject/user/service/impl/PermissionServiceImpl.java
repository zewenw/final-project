package com.finalproject.user.service.impl;

import com.finalproject.user.convert.PermissionMapper;
import com.finalproject.user.dto.request.PermissionRequest;
import com.finalproject.user.dto.response.PermissionResponse;
import com.finalproject.user.entity.Permission;
import com.finalproject.user.entity.Role;
import com.finalproject.user.repository.PermissionRepository;
import com.finalproject.user.repository.RoleRepository;
import com.finalproject.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean checkDuplicatePermission(String permissionCode, String permissionType) {
        Optional<Permission> one = permissionRepository.findOne(Example.of(Permission.builder()
                .permissionCode(permissionCode)
                .permissionType(permissionType)
                .build()));
        return one.isPresent();
    }

    @Override
    public PermissionResponse addPermission(PermissionRequest permissionRequest) {
        Permission permission = permissionRepository.save(PermissionMapper.MAPPER
                .permissionRequestToPermission(permissionRequest));
        return PermissionMapper.MAPPER.permissionToPermissionResponse(permission);
    }

    @Override
    public PermissionResponse getPermission(String permissionCode) {
        Permission permission = permissionRepository.findByPermissionCode(permissionCode);
        return PermissionMapper.MAPPER.permissionToPermissionResponse(permission);
    }

    @Override
    public PermissionResponse updatePermission(PermissionRequest permissionRequest) {
        Optional<Permission> permission = permissionRepository.findById(permissionRequest.getId());
        if(permission.isPresent()){
            Permission currentPermission = permission.get();
            currentPermission.setPermissionName(permissionRequest.getPermissionName());
            currentPermission.setPermissionDescription(permissionRequest.getPermissionDescription());
            currentPermission.setPermissionCode(permissionRequest.getPermissionCode());
            Permission save = permissionRepository.save(currentPermission);
            return PermissionMapper.MAPPER.permissionToPermissionResponse(save);
        }
        return null;
    }

    @Override
    public void deletePermissionById(long id) {
        //todo delete permission role mapping
        permissionRepository.deleteById(id);
    }

    @Override
    public Page<PermissionResponse> getPermissionByPage(PermissionRequest request) {
        //TODO page sorting and find user by fields
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize()
                , Sort.by(Sort.Direction.DESC, "id"));
        Page<Permission> page = permissionRepository.findAll(pageable);
        List<Permission> content = page.getContent();
        List<PermissionResponse> userResponses = PermissionMapper.MAPPER.permissionsToPermissionResponses(content);
        return new PageImpl<>(userResponses, pageable, page.getTotalElements());
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return PermissionMapper.MAPPER.permissionsToPermissionResponses(permissions);
    }

    @Override
    public List<PermissionResponse> getPermissionByRoleId(Long roleId) {
        Optional<List<Permission>> optionalList = Optional.ofNullable(permissionRepository.findByRoleId(roleId));
        return optionalList.map(PermissionMapper.MAPPER:: permissionsToPermissionResponses).orElse(null);
    }

    @Override
    public Boolean unbindPermissionWithRole(String roleId, String permissionId) {
        Optional<Role> optionalRole = roleRepository.findById(Long.valueOf(roleId));
        Optional<Permission> optionalPermission = permissionRepository.findById(Long.valueOf(permissionId));
        if( optionalRole.isPresent() && optionalPermission.isPresent()){
            Role role = optionalRole.get();
            Permission permission = optionalPermission.get();
            Set<Permission> permissions = role.getPermissions();
            permissions.remove(permission);
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public Boolean bindPermissionWithRole(String roleId, String permissionId) {
        Optional<Role> optionalRole = roleRepository.findById(Long.valueOf(roleId));
        Optional<Permission> optionalPermission = permissionRepository.findById(Long.valueOf(permissionId));
        if( optionalRole.isPresent() && optionalPermission.isPresent()){
            Role role = optionalRole.get();
            role.getPermissions().add(optionalPermission.get());
            roleRepository.save(role);
            return true;
        }
        return false;
    }
}
