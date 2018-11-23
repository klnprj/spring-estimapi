package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.request.BuildingCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ManageBuilding {

    Building get(Long id) throws BuildingMissingException;

    Building create(@NotNull UserId userId, @NotNull @Valid BuildingCreateRequest request);

    Building update(@NotNull Long id, @NotNull @Valid BuildingCreateRequest request) throws BuildingMissingException;


    // default implementation
    @Service
    @Validated
    @AllArgsConstructor
    class Default implements ManageBuilding {

        private BuildingRepository buildings;

        @Override
        public Building get(Long id) throws BuildingMissingException {
            return buildings.get(id).orElseThrow(() -> new BuildingMissingException(id));
        }

        @Override
        public Building create(@NotNull UserId authorId, @NotNull @Valid BuildingCreateRequest request) {
            Building building = new Building(authorId, request.name(), request.address(), request.location(), request.description(), request.status(), request.clientId(), request.projectId());
            buildings.add(building);
            return building;
        }

        @Override
        public Building update(Long id, BuildingCreateRequest request) throws BuildingMissingException {
            Building building = buildings.get(id).orElseThrow(() -> new BuildingMissingException(id));
            buildings.update(building.with(request.name(), request.address(), request.location(), request.description(), request.status(), request.clientId(), request.projectId()));
            return building;
        }
    }
}
