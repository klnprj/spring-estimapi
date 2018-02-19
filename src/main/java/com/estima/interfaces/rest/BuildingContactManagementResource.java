package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/buildings/{buildingId}/contacts")
public class BuildingContactManagementResource {

    @GetMapping("/")
    public ResponseEntity list(@PathVariable("buildingId") Long buildingId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity add(@PathVariable("buildingId") Long buildingId, @RequestBody Map contact) {
        return ResponseEntity.created(URI.create("/api/buildings/" + buildingId + "/contacts")).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("buildingId") Long buildingId, @PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
