package com.estima.interfaces.rest;

import com.estima.app.UsersSource;
import com.estima.domain.User;
import com.estima.domain.UserId;
import com.estima.domain.ex.UserMissingException;
import com.estima.interfaces.rest.representation.UserRepresentation;
import com.estima.interfaces.rest.request.UserCreateRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserRepresentation> get(@PathVariable String id) throws UserMissingException {
        User currentUser = users.get(UserId.of(id));
        return ResponseEntity.ok(new UserRepresentation(currentUser));
    }

    @PostMapping
    public ResponseEntity<UserRepresentation> create(@RequestBody UserCreateRequest request) {
        User user = users.create(request);
        return ResponseEntity.ok(new UserRepresentation(user));
    }
}
