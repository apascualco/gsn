package com.apascualco.user.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class User {

    UUID uuid;
    String user;
    @With String password;
    String name;
    String surname;
    boolean active;
    Set<String> roles;
}
