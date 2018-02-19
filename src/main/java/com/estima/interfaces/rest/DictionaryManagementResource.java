package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionaries")
public class DictionaryManagementResource {

    @GetMapping("/")
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity get(@PathVariable("key") String key) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}/count")
    public ResponseEntity count(@PathVariable("key") String key) {
        return ResponseEntity.ok().build();
    }
}
