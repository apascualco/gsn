package com.apascualco.user.service;

import com.apascualco.user.domain.User;
import com.apascualco.user.exception.UserValidation;
import com.apascualco.user.port.UserPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserSignUpTest {

    private final UserPersistence userPersistence;
    private final UserService userService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final User defaultUser;

    private final static String USER = "apascualco@gmail.com";
    private final static String PASSWORD = "admin";
    private final static String NAME = "Alberto";
    private final static String SUERNAME = "Pascual";

    public UserSignUpTest() {
        this.userPersistence =  mock(UserPersistence.class);
        this.bcryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        this.userService = new UserServiceImpl(this.userPersistence, bcryptPasswordEncoder);
        this.defaultUser = User.builder().user(USER).password(PASSWORD).name(NAME).surname(SUERNAME).build();
    }

    @Test
    public void register_new_user() throws Throwable {
        userService.signUp(defaultUser);
        verify(userPersistence, Mockito.times(1)).save(any());
        verify(bcryptPasswordEncoder, Mockito.times(1)).encode(PASSWORD);
    }

    @Test
    public void register_new_user_without_password_should_return_exception() {
        assertThrows(UserValidation.class, () -> userService.signUp(
                User.builder()
                        .user(USER)
                        .password(null)
                        .name(NAME)
                        .surname(SUERNAME)
                        .build()
                )
        );
    }

    @Test
    public void register_new_user_without_user_should_return_exception() {
        assertThrows(UserValidation.class, () -> userService.signUp(
                User.builder()
                        .user(null)
                        .password(PASSWORD)
                        .name(NAME)
                        .surname(SUERNAME)
                        .build()
                )
        );
    }

    @Test
    public void register_new_user_already_exist_should_return_exception() {
        when(this.userPersistence.getUserByUser(USER)).thenReturn(defaultUser);
        assertThrows(UserValidation.class, () -> userService.signUp(defaultUser));
        verify(userPersistence, Mockito.times(0)).save(any());
        verify(bcryptPasswordEncoder, Mockito.times(0)).encode(PASSWORD);
    }
}
