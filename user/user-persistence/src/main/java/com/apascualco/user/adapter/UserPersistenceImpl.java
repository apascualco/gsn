package com.apascualco.user.adapter;

import com.apascualco.user.conversor.UserConverter;
import com.apascualco.user.data.UserRepository;
import com.apascualco.user.domain.User;
import com.apascualco.user.port.UserPersistence;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserPersistenceImpl implements UserPersistence {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @SneakyThrows
    public User getUserByUser(final String user) {
        return userConverter.map(userRepository.findByUser(user));
    }

    @SneakyThrows
    public User save(final User user) {
        return userConverter.map(userRepository.save(userConverter.map(user)));
    }

}
