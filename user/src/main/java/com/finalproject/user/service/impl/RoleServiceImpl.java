package com.finalproject.user.service.impl;

import com.finalproject.user.convert.RoleMapper;
import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import com.finalproject.user.entity.Role;
import com.finalproject.user.entity.User;
import com.finalproject.user.entity.view.RoleView;
import com.finalproject.user.repository.RoleRepository;
import com.finalproject.user.repository.UserRepository;
import com.finalproject.user.service.RoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public RoleResponse updateRole(RoleRequest roleRequest) {
        Optional<Role> optional = roleRepository.findById(roleRequest.getId());
        if(optional.isPresent()){
            Role currentRole = optional.get();
            currentRole.setRoleName(roleRequest.getRoleName());
            currentRole.setRoleDescription(roleRequest.getRoleDescription());
            Role role = roleRepository.save(currentRole);
            return RoleMapper.MAPPER.roleToRoleResponse(role);
        }
        return null;
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
    public Page<RoleResponse> getRoleByPage(RoleRequest roleRequest) {
        Pageable pageable = PageRequest.of(roleRequest.getPageNo(), roleRequest.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Role> page = roleRepository.findAll(pageable);
        List<Role> content = page.getContent();
        List<RoleResponse> userResponses = RoleMapper.MAPPER.rolesToRoleResponse(content);
        return new PageImpl<>(userResponses, pageable, page.getTotalElements());
    }

    @Override
    public List<RoleResponse> getOwnedRoleByUsername(String username) {
        Optional<List<Role>> optionalRoles = Optional.ofNullable(roleRepository.findOwnedRolesByUsername(username));
        return RoleMapper.MAPPER.rolesToRoleResponse(optionalRoles.orElse(Lists.newArrayList()));
    }

    @Override
    public List<RoleResponse> getLackedRoleByUsername(String username) {
        Optional<List<RoleView>> roleViews = Optional.ofNullable(roleRepository.findLackedRolesByUsername(username));
        return RoleMapper.MAPPER.roleViewsToRoleResponse(roleViews.orElse(Lists.newArrayList()));
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

    @Override
    public Boolean unbindUserWithRole(long roleId, String username) {
        Optional<Role> role = roleRepository.findById(roleId);
        if(role.isPresent()){
            User user = userRepository.findUserByUsername(username);
            Set<Role> roles = user.getRoles();
            roles.remove(role.get());
            userRepository.save(user);
        }
        return true;
    }

    @Override
    public List<String> getRoleCodeByUsername(String username) {
        return roleRepository.findOwnedRolesByUsername(username)
                .stream().map(Role::getRoleCode).collect(Collectors.toList());
    }
}
