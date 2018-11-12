package com.estima.app;

import com.estima.domain.User;
import com.estima.domain.UserId;
import com.estima.domain.ex.UserMissingException;
import com.estima.domain.UserRepository;
import com.estima.interfaces.rest.request.UserCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class Users implements UsersSource {

    private UserRepository users;

    @Override
    public User profile() throws UserMissingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        UserId currentUserId = UserId.of(currentUsername);
        return users.get(currentUserId).orElseThrow(() -> new UserMissingException(currentUserId));
    }

    @Override
    public Collection<User> all() {
        // todo: remove filtering
        return users.asList().stream().filter(Objects::nonNull).collect(toList());
    }

    @Override
    public User get(UserId userId) throws UserMissingException {
        return users.get(userId).orElseThrow(() -> new UserMissingException(userId));
    }

    @Override
    public User create(@NotNull @Valid UserCreateRequest request) {
        User user = request.asUser();
        users.add(request.asUser());
        return user;
    }
}
