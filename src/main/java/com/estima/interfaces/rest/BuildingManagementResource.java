package com.estima.interfaces.rest;

import com.estima.app.ManageBuilding;
import com.estima.app.QueryBuilding;
import com.estima.domain.Building;
import com.estima.domain.BuildingSelection;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.representation.BuildingRepresentation;
import com.estima.interfaces.rest.representation.BuildingSelectionRepresentation;
import com.estima.interfaces.rest.request.BuildingCreateRequest;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/buildings")
@AllArgsConstructor
public class BuildingManagementResource {

    private ManageBuilding buildings;
    private QueryBuilding buildingQuerier;

    @GetMapping
    public ResponseEntity<BuildingSelectionRepresentation> search(BuildingSearchRequest request) {
        BuildingSelection buildingSelection = buildingQuerier.query(request);
        return ResponseEntity.ok(new BuildingSelectionRepresentation(buildingSelection));
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
