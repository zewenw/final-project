package com.finalproject.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(
        name = "permissions",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "permission_name_unique",
                        columnNames = "permission_name"
                ),
                @UniqueConstraint(
                        name = "permission_code_unique",
                        columnNames = "permission_code"
                )
        }
)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @Column(name = "permission_code", nullable = false)
    private String permissionCode;

    @Column(name = "permission_type", nullable = false)
    private String permissionType;

    @Column(name = "permission_description")
    private String permissionDescription;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;


}
