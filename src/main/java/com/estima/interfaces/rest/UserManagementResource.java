package com.estima.interfaces.rest;

import com.estima.app.UsersSource;
import com.estima.domain.User;
import com.estima.domain.ex.UserMissingException;
import com.estima.interfaces.rest.representation.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/users")
public class UserManagementResource {

    private UsersSource users;

    public UserManagementResource(UsersSource users) {
        this.users = users;
    }

    @GetMapping
    public ResponseEntity<Collection<UserRepresentation>> list() {
        Collection<User> userCollection = users.all();
        return ResponseEntity.ok(userCollection.stream().map(UserRepresentation::new).collect(toList()));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserRepresentation> current() throws UserMissingException {
        User currentUser = users.profile();
        return ResponseEntity.ok(new UserRepresentation(currentUser));
    }
}
