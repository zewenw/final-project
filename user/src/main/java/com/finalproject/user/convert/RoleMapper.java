package com.finalproject.user.convert;

import com.finalproject.user.dto.request.RoleRequest;
import com.finalproject.user.dto.response.RoleResponse;
import com.finalproject.user.entity.Role;
import com.finalproject.user.entity.view.RoleView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    RoleResponse roleToRoleResponse(Role role);

    Role RoleRequestToRole(RoleRequest roleRequest);

    List<RoleResponse> rolesToRoleResponse(List<Role> roles);
    List<RoleResponse> roleViewsToRoleResponse(List<RoleView> roles);

}
