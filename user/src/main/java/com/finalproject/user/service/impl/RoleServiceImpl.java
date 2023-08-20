package com.finalproject.user.service.impl;

import com.finalproject.user.convert.RoleMapper;
import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import com.finalproject.user.entity.Role;
import com.finalproject.user.entity.User;
import com.finalproject.user.repository.RoleRepository;
import com.finalproject.user.repository.UserRepository;
import com.finalproject.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean checkDuplicateRoleCode(String roleCode) {
        return roleRepository.findByRoleCode(roleCode) != null;
    }

    @Override
    public boolean checkDuplicateRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName) != null;
    }

    @Override
    public RoleResponse addRole(RoleRequest roleRequest) {
        Role role = roleRepository.save(RoleMapper.MAPPER.RoleRequestToRole(roleRequest));
        return RoleMapper.MAPPER.roleToRoleResponse(role);
    }

    @Override
    public RoleResponse getRole(String rolecode) {
        Role role = roleRepository.findByRoleCode(rolecode);
        return RoleMapper.MAPPER.roleToRoleResponse(role);
    }

    @Override
    public RoleResponse udpateRole(RoleRequest roleRequest) {
        Role currentRole = roleRepository.findByRoleCode(roleRequest.getRoleCode());
        currentRole.setRoleName(roleRequest.getRoleName());
        currentRole.setRoleDescription(roleRequest.getRoleDescription());
        Role role = roleRepository.save(currentRole);
        return RoleMapper.MAPPER.roleToRoleResponse(role);
    }


    @Override
    public void deleteRoleById(long id) {
        //todo delete role permission mapping and user role mapping
        roleRepository.deleteById(id);

    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        return RoleMapper.MAPPER.rolesToRoleResponse(roles);
    }

    @Override
    public Boolean bindUserWithRole(long roleId, String username) {
        Optional<Role> role = roleRepository.findById(roleId);
        if(role.isPresent()){
            User user = userRepository.findUserByUsername(username);
            Set<Role> roles = user.getRoles();
            roles.add(role.get());
            userRepository.save(user);
        }
        return true;
    }
}
