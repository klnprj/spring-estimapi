package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/dealers")
public class DealerManagementResource {

    @GetMapping("/")
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Map dealer) {
        return ResponseEntity.created(URI.create("/api/dealers/" + 1)).build();
    }
}
