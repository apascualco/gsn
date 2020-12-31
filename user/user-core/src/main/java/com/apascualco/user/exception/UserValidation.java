package com.apascualco.user.exception;

@SuppressWarnings("unused")
public class UserValidation extends Exception {

    public UserValidation() {
    }

    public UserValidation(final String message) {
        super(message);
    }
}
