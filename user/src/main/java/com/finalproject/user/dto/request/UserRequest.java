package com.finalproject.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    @NotBlank(message = "username must be present")
    private String username;

    @NotBlank(message = "email must be present")
    private String email;

    @NotBlank(message = "password must be present")
    private String password;

    @NotBlank(message = "enabled must be present")
    private boolean enabled;
}
