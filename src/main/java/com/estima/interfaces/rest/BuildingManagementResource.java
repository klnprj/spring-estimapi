package com.estima.interfaces.rest;

import com.estima.app.BuildingSelection;
import com.estima.domain.Building;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.representation.BuildingRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/buildings")
@AllArgsConstructor
public class BuildingManagementResource {

    private BuildingSelection buildings;

    @GetMapping
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingRepresentation> get(@PathVariable("id") Long id) throws BuildingMissingException {
        Building building = buildings.get(id);
        return ResponseEntity.ok(new BuildingRepresentation(building));
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
