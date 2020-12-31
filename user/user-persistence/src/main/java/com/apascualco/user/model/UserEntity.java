package com.apascualco.user.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "user")
public class UserEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String user;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "is_active",columnDefinition = "boolean default true")
    private boolean active;

    @Column
    @ElementCollection
    @CollectionTable(
            name = "role",
            foreignKey = @ForeignKey(name = "user_role_fk"),
            joinColumns = @JoinColumn(name = "user_uuid"))
    private Set<String> roles;

}
