package com.finalproject.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse implements Serializable {

    @Schema(title = "id")
    private long id;

    @Schema(title = "permissionName")
    private String permissionName;

    @Schema(title = "permissionCode")
    private String permissionCode;

    @Schema(title = "permissionType")
    private String permissionType;

    @Schema(title = "permissionDescription")
    private String permissionDescription;

    @Schema(title = "dateCreated")
    private LocalDateTime dateCreated;

    @Schema(title = "lastUpdated")
    private LocalDateTime lastUpdated;
}
