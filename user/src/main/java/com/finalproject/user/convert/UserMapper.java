package com.finalproject.user.convert;

import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserRequest userToUserRequest(User user);

    User userRequestToUser(UserRequest userRequest);

}
