package com.finalproject.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends PagableRequest implements Serializable {

    @NotBlank(message = "username must be present")
    private String username;

    @NotBlank(message = "email must be present")
    private String email;

    @NotBlank(message = "password must be present")
    private String password;

    @NotBlank(message = "enabled must be present")
    private boolean enabled;
}
