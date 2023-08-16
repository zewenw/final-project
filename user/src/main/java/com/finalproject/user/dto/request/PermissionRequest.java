package com.finalproject.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest implements Serializable {

    @NotBlank(message = "permissionName must be present")
    private String permissionName;

    @NotBlank(message = "permissionCode must be present")
    private String permissionCode;

    @NotBlank(message = "permissionType must be present")
    private String permissionType;

    @NotBlank(message = "permissionDescription must be present")
    private String permissionDescription;
}
