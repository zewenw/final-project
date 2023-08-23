package com.finalproject.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest extends PagableRequest implements Serializable {

    private long id;

    @NotBlank(message = "roleCode must be present")
    private String roleCode;

    @NotBlank(message = "roleName must be present")
    private String roleName;

    @NotBlank(message = "roleDescription must be present")
    private String roleDescription;
}
