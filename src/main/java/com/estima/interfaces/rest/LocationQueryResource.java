package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationQueryResource {

    @GetMapping("/")
    public ResponseEntity query() {
        return ResponseEntity.ok().build();
    }
}
