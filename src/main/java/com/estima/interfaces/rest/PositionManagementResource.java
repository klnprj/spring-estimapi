package com.estima.interfaces.rest;

import com.estima.app.ManageBuilding;
import com.estima.domain.Position;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.representation.PositionRepresentation;
import com.estima.interfaces.rest.representation.PositionSelectionRepresentation;
import com.estima.interfaces.rest.request.PositionCreateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
// TODO: move under building resource ("/api/buildings/<id>/positions") ?
@RequestMapping("/api/positions")
@AllArgsConstructor
public class PositionManagementResource {

    private final ManageBuilding manageBuilding;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<PositionSelectionRepresentation> list(@RequestParam Long buildingId) throws BuildingMissingException {
        Collection<Position> positions = manageBuilding.buildingPositions(buildingId);
        return ResponseEntity.ok(new PositionSelectionRepresentation(positions));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity add(@RequestBody PositionCreateRequest request) throws BuildingMissingException, JsonProcessingException {
        Position position = manageBuilding.addPosition(request);
        // FIXME: remove explicit json conversion (problem when implicit objectMapper used it produces incorrect date format)
        // BTW: where ResponseEntity is processed? where JsonProcessingException is processed? AbstractJackson2HttpMessageConverter?
        String representation = objectMapper.writeValueAsString(new PositionRepresentation(position));
        return new ResponseEntity<>(representation, HttpStatus.CREATED);
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
