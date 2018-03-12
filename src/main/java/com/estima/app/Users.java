package com.estima.app;

import com.estima.domain.User;
import com.estima.domain.UserId;
import com.estima.domain.UserNotFoundException;
import com.estima.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class Users implements UsersSource {

    private UserRepository users;

    @Override
    public User profile() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        UserId currentUserId = UserId.of(currentUsername);
        return users.get(currentUserId).orElseThrow(() -> new UserNotFoundException(currentUserId));
    }

    @Override
    public Collection<User> all() {
        // todo: remove filtering
        return users.asList().stream().filter(Objects::nonNull).collect(toList());
    }
}
