package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/buildings")
public class BuildingManagementResource {

    @GetMapping
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Map building) {
        return ResponseEntity.created(URI.create("/api/buildings/" + 1)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Map building) {
        return ResponseEntity.noContent().build();
    }
}
