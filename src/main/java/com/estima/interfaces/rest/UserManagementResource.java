package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserManagementResource {

    @GetMapping("/")
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Map user) {
        return ResponseEntity.created(URI.create("/api/users/" + 1)).build();
    }

    @GetMapping("/profile")
    public ResponseEntity current() {
        return ResponseEntity.ok().build();
    }
}
