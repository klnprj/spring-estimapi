package com.estima.app;

import com.estima.domain.User;
import com.estima.domain.UserId;
import com.estima.domain.ex.UserMissingException;
import com.estima.interfaces.rest.request.UserCreateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface UsersSource {

    User profile() throws UserMissingException;
    Collection<User> all();

    User get(@NotNull UserId userId) throws UserMissingException;

    User create(@NotNull @Valid UserCreateRequest request);
}
