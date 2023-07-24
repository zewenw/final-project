package com.finalproject.gateway.repository;

import java.util.List;

public interface RoleRepository {
    List<String> selectUrlRole(String path);

//    List<Roles> selectUrlRole(String url);
}