package com.estima.app;

import com.estima.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class Users implements UsersSource {


    @Override
    public User profile() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Collection<User> all() {
        throw new UnsupportedOperationException("not implemented");
    }
}
