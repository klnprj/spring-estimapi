package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.domain.BuildingRepository;
import com.estima.interfaces.rest.request.BuildingCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Validated
@AllArgsConstructor
public class BuildingSelectionImpl implements BuildingSelection {

    private BuildingRepository buildings;

    @Override
    public Building get(Long id) throws BuildingMissingException {
        return buildings.get(id).orElseThrow(() -> new BuildingMissingException(id));
    }

    @Override
    public Collection<Building> query() {
        return buildings.asList();
    }

    @Override
    public Building create(@NotNull UserId authorId, @NotNull @Valid BuildingCreateRequest request) {
        Building building = new Building(authorId, request.name(), request.address(), request.location(), request.description(), request.status(), request.clientId(), request.projectId());
        buildings.add(building);
        return building;
    }
}
