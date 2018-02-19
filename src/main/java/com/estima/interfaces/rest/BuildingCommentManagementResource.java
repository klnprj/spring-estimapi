package com.estima.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/buildings/{id}/comments")
public class BuildingCommentManagementResource {

    @GetMapping("/")
    public ResponseEntity list(@PathVariable("id") Long buildingId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity add(@PathVariable("id") Long buildingId, @RequestBody Map comment) {
        return ResponseEntity.created(URI.create("/api/buildings/" + buildingId + "/comments/" + 1)).build();
    }
}
