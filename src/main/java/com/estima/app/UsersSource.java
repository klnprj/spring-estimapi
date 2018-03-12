package com.estima.app;

import com.estima.domain.User;
import com.estima.domain.UserNotFoundException;

import java.util.Collection;

public interface UsersSource {

    User profile() throws UserNotFoundException;
    Collection<User> all();
}
