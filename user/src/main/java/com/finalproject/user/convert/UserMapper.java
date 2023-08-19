package com.finalproject.user.convert;

import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserRequest userToUserRequest(User user);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);


    List<UserResponse> usersToUserReponses(List<User> allUsers);
}
