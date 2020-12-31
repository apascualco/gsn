package com.apascualco.user.conversor;

import com.apascualco.user.domain.User;
import com.apascualco.user.model.UserEntity;

public interface UserConverter {
    UserEntity map(final User user);
    User map(final UserEntity userEntity);
}
