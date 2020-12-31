package com.apascualco.user.service;

import com.apascualco.user.exception.UserValidation;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.apascualco.user.exception.Assert.assertion;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTest {

    @Test
    public void assert_should_throw_custom_exception() {
        assertThrows(UserValidation.class, () -> assertion("random").verify(Objects::nonNull).thenThrow(UserValidation::new));
    }

    @Test
    public void assert_should_throw_custom_exception_with_custom_predicate() {

        assertThrows(
                UserValidation.class,
                () -> assertion("random").verify(string -> nonNull(string) && "random".equals(string)).thenThrow(UserValidation::new)
        );
    }

    @Test
    public void assert_should_throw_custom_exception_with_custom_message() {
        final String expectedMessage = "Random is present";
        final UserValidation userValidation = assertThrows(
                UserValidation.class,
                () -> assertion("random").verify(Objects::nonNull).thenThrow(UserValidation::new, expectedMessage)
        );
        assertTrue(userValidation.getMessage().contains(expectedMessage));
    }

    @Test
    public void assert_should_throw_custom_exception_should_have_predicate() {
        assertThrows(AssertionError.class, () -> assertion("random").thenThrow(UserValidation::new));
    }

    @Test
    public void assert_should_throw_custom_exception_should_have_exception() {
        assertion("random").verify(Objects::nonNull);
    }
}
