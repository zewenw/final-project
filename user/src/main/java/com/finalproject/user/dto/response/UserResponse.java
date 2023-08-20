package com.finalproject.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private long id;

    private String username;

    private String email;

    private String password;

    private boolean enabled;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    private Set<RoleResponse> roles = new HashSet<>();
}
