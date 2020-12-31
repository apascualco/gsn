package com.apascualco.user.service;

import com.apascualco.user.domain.User;

public interface UserService {

    @SuppressWarnings("squid:S112")
    void signUp(User build) throws Throwable;

}
