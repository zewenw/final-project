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
public class PermissionRequest extends PagableRequest implements Serializable {

    private long id;

    @NotBlank(message = "permissionName must be present")
    private String permissionName;

    @NotBlank(message = "permissionCode must be present")
    private String permissionCode;

    @NotBlank(message = "permissionType must be present")
    private String permissionType;

    @NotBlank(message = "permissionDescription must be present")
    private String permissionDescription;
}
