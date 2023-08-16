package com.finalproject.user.convert;

import com.finalproject.user.dto.request.PermissionRequest;
import com.finalproject.user.dto.response.PermissionResponse;
import com.finalproject.user.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper MAPPER = Mappers.getMapper(PermissionMapper.class);

    PermissionResponse permissionToPermissionResponse(Permission permission);

    Permission permissionRequestToPermission(PermissionRequest permissionRequest);
}
