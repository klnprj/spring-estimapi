package com.estima.domain;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Collection;

// building selection contains collection of selected buildings and total count
@Getter
@Accessors(fluent = true)
public class BuildingSelection {
    private final Collection<Building> buildings;
    private final int totalCount;

    public BuildingSelection(Collection<Building> buildings, int totalCount) {
        this.buildings = buildings;
        this.totalCount = totalCount;
    }
}
