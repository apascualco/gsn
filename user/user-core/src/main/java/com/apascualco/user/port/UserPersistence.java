package com.apascualco.user.port;

import com.apascualco.user.domain.User;
import com.apascualco.user.exception.UserValidation;

public interface UserPersistence {

    User getUserByUser(String user);
    User save(User user);
}
