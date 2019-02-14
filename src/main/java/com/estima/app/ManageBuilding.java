package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import com.estima.domain.Position;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.domain.ex.PositionMissingException;
import com.estima.interfaces.rest.request.BuildingCreateRequest;
import com.estima.interfaces.rest.request.PositionCreateRequest;
import com.estima.interfaces.rest.request.PositionUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface ManageBuilding {

    Building get(@NotNull Long id) throws BuildingMissingException;

    Building create(@NotNull UserId userId, @NotNull @Valid BuildingCreateRequest request);

    Building update(@NotNull Long id, @NotNull @Valid BuildingCreateRequest request) throws BuildingMissingException;

    Position addPosition(@NotNull @Valid PositionCreateRequest request) throws BuildingMissingException;

    Collection<Position> buildingPositions(@NotNull Long buildingId) throws BuildingMissingException;

    Position getPosition(@NotNull Long positionId) throws PositionMissingException;

    void removePosition(@NotNull Long positionId) throws PositionMissingException;

    Position updatePosition(@NotNull Long positionId, @NotNull @Valid PositionUpdateRequest request) throws PositionMissingException;


    // default implementation
    @Service
    @Validated
    @AllArgsConstructor
    class Default implements ManageBuilding {

        private final BuildingRepository buildings;

        @Override
        public Building get(@NotNull Long id) throws BuildingMissingException {
            return buildings.get(id).orElseThrow(() -> new BuildingMissingException(id));
        }

        @Override
        public Building create(@NotNull UserId authorId, @NotNull @Valid BuildingCreateRequest request) {
            Building building = new Building(authorId, request.name(), request.address(), request.location(), request.description(), request.status(), request.clientId(), request.projectId());
            buildings.add(building);
            return building;
        }

        @Override
        public Building update(@NotNull Long id, @NotNull @Valid BuildingCreateRequest request) throws BuildingMissingException {
            Building building = buildings.get(id).orElseThrow(() -> new BuildingMissingException(id));
            buildings.update(building.with(request.name(), request.address(), request.location(), request.description(), request.status(), request.clientId(), request.projectId()));
            return building;
        }

        @Override
        public Position addPosition(@NotNull @Valid PositionCreateRequest request) throws BuildingMissingException {
            Building building = buildings.get(request.buildingId()).orElseThrow(() -> new BuildingMissingException(request.buildingId()));
            Position newPosition = request.asPosition();

            building.addPosition(newPosition);
            buildings.update(building);

            return building.positionList().get(building.getPositions().size() - 1);
        }

        @Override
        public Collection<Position> buildingPositions(@NotNull Long buildingId) throws BuildingMissingException {
            Building building = buildings.get(buildingId).orElseThrow(() -> new BuildingMissingException(buildingId));

            return building.getPositions();
        }

        @Override
        public Position getPosition(@NotNull Long positionId) throws PositionMissingException {
            // TODO: remove after positions are refactored within building aggregate
            Building building = buildings.getPositionBuilding(positionId).orElseThrow(() -> new PositionMissingException(positionId));
            return building.position(positionId);
        }

        @Override
        public void removePosition(@NotNull Long positionId) throws PositionMissingException {
            Building building = buildings.getPositionBuilding(positionId).orElseThrow(() -> new PositionMissingException(positionId));

            building.removePosition(positionId);

            buildings.update(building);
        }

        @Override
        public Position updatePosition(@NotNull Long positionId, @NotNull @Valid PositionUpdateRequest request) throws PositionMissingException {
            Building building = buildings.getPositionBuilding(positionId).orElseThrow(() -> new PositionMissingException(positionId));
            Position position = building.position(positionId);

            position.updatedWith(request.contactName(), request.type(), request.spec(),
                    request.grossPrice(), request.total(), request.status(), request.dateShipped(), request.dealerPrice(),
                    request.quantity());

            buildings.update(building);

            return position;
        }
    }
}
