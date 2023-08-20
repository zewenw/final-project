package com.finalproject.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements Serializable {

    private long id;

    private String roleCode;

    private String roleName;

    private String roleDescription;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

}
