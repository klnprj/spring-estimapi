package com.estima.app;

import com.estima.domain.User;

import java.util.Collection;

public interface UsersSource {

    User profile();
    Collection<User> all();
}
