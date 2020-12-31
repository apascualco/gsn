package com.apascualco.user.conversor;

import com.apascualco.user.domain.User;
import com.apascualco.user.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    public UserEntity map(final User user) {
        return UserEntity.builder()
                .uuid(user.getUuid())
                .user(user.getUser())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .active(user.isActive())
                .roles(user.getRoles())
                .build();
    }

    public User map(final UserEntity userEntity) {
        return User.builder()
                .uuid(userEntity.getUuid())
                .user(userEntity.getUser())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .active(userEntity.isActive())
                .roles(userEntity.getRoles())
                .build();
    }
}
