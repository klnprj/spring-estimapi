package com.estima.interfaces.rest;

import com.estima.app.LocateBuilding;
import com.estima.app.QueryBuilding;
import com.estima.domain.BuildingSelection;
import com.estima.interfaces.rest.representation.BuildingSelectionRepresentation;
import com.estima.interfaces.rest.request.BuildingLocateRequest;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
public class LocationQueryResource {

    private final QueryBuilding buildingQuerier;
    private final LocateBuilding buildingLocator;

    @GetMapping
    public ResponseEntity query(BuildingSearchRequest searchRequest, BuildingLocateRequest locateRequest) {
        BuildingSelection buildingSelection;
        if (!locateRequest.isEmpty()) {
            buildingSelection = buildingLocator.locate(locateRequest);
        } else {
            buildingSelection = buildingQuerier.query(searchRequest);
        }
        return ResponseEntity.ok(new BuildingSelectionRepresentation(buildingSelection));
    }
}
