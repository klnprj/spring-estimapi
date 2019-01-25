package com.estima.interfaces.rest.representation;

import com.estima.domain.Building;
import lombok.Getter;

@Getter
public class BuildingRefRepresentation {
    private final long id;

    public BuildingRefRepresentation(Building building) {
        this.id = building.getId();
    }
}
