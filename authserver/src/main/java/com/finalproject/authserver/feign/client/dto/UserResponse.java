package com.finalproject.authserver.feign.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
}
