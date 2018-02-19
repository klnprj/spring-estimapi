package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/positions")
public class PositionManagementResource {

    @GetMapping("/")
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Map position) {
        return ResponseEntity.created(URI.create("/api/positions" + 1)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Map position) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
