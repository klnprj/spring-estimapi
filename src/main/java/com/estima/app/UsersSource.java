package com.estima.app;

import com.estima.domain.User;
import com.estima.domain.ex.UserMissingException;

import java.util.Collection;

public interface UsersSource {

    User profile() throws UserMissingException;
    Collection<User> all();
}
