package com.finalproject.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse implements Serializable {

    private long id;
    private String permissionName;
    private String permissionCode;
    private String permissionType;
    private String permissionDescription;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
}
