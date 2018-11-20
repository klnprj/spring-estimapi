package com.estima.interfaces.rest;

import com.estima.app.BuildingSelection;
import com.estima.domain.Building;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.representation.BuildingRepresentation;
import com.estima.interfaces.rest.request.BuildingCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/buildings")
@AllArgsConstructor
public class BuildingManagementResource {

    private BuildingSelection buildings;

    @GetMapping
    public ResponseEntity<Collection<BuildingRepresentation>> list() {
        Collection<Building> buildingCollection = buildings.query();
        return ResponseEntity.ok(buildingCollection.stream().map(BuildingRepresentation::new).collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingRepresentation> get(@PathVariable("id") Long id) throws BuildingMissingException {
        Building building = buildings.get(id);
        return ResponseEntity.ok(new BuildingRepresentation(building));
    }

    @PostMapping
    public ResponseEntity<BuildingRepresentation> add(
            Principal principal,
            @RequestBody BuildingCreateRequest request) {
        UserId authorId = UserId.of(principal.getName());
        Building building = buildings.create(authorId, request);
        return ResponseEntity.ok(new BuildingRepresentation(building));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BuildingCreateRequest request) throws BuildingMissingException {
        Building building = buildings.update(id, request);
        return ResponseEntity.ok(new BuildingRepresentation(building));
    }
}
