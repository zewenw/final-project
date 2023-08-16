package com.finalproject.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements Serializable {

    private String username;

    private String email;

    private String password;

    private boolean enabled;
}
