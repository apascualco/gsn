package com.apascualco.user.service;

import com.apascualco.user.domain.User;
import com.apascualco.user.exception.UserValidation;
import com.apascualco.user.port.UserPersistence;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.apascualco.user.exception.Assert.assertion;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final  UserPersistence userPersistence;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public void signUp(final User user) throws Throwable {
        assertion(user).verify(verifyUser -> isNull(verifyUser.getPassword())).thenThrow(UserValidation::new);
        assertion(user).verify(verifyUser -> isNull(verifyUser.getUser())).thenThrow(UserValidation::new);
        assertion(user).verify(verifyUser -> nonNull(userPersistence.getUserByUser(user.getUser()))).thenThrow(UserValidation::new);
        userPersistence.save(user.withPassword(bcryptPasswordEncoder.encode(user.getPassword())));
    }
}
