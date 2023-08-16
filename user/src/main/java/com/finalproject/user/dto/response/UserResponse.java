package com.finalproject.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private long id;

    private String username;

    private String email;

    private String password;

    private boolean enabled;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;
}
