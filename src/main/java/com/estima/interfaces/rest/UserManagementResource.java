package com.estima.interfaces.rest;

import com.estima.app.UsersSource;
import com.estima.domain.User;
import com.estima.domain.UserNotFoundException;
import com.estima.interfaces.rest.representation.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
//    public ResponseEntity<Collection<Map>> list() {
//        Map<String, Object> u = new HashMap<>();
//        u.put("id", "id");
//        return ResponseEntity.ok(Stream.of(u).collect(toList()));
//    }

    @GetMapping("/profile")
    public ResponseEntity<UserRepresentation> current() throws UserNotFoundException {
        User currentUser = users.profile();
        return ResponseEntity.ok(new UserRepresentation(currentUser));
    }
//    public ResponseEntity<Map> current() throws UserNotFoundException {
//        Map<String, Object> u = new HashMap<>();
//        u.put("id", "id");
//        return ResponseEntity.ok(u);
//    }
}
