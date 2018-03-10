package com.estima.interfaces.rest;

import com.estima.app.UsersSource;
import com.estima.interfaces.rest.representation.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserManagementResource {

    private UsersSource users;

    public UserManagementResource(UsersSource users) {
        this.users = users;
    }

    @GetMapping("/")
    public ResponseEntity<Collection<UserRepresentation>> list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserRepresentation> current() {

        return ResponseEntity.ok().build();
    }
}
