package com.estima.interfaces.rest.representation;

import com.estima.domain.BuildingSelection;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
public class BuildingSelectionRepresentation {

    private final Collection<BuildingRepresentation> buildingList;
    private final int totalCount;

    public BuildingSelectionRepresentation(BuildingSelection buildingSelection) {
        this.buildingList = Optional.ofNullable(buildingSelection.buildings()).map(l -> l.stream().map(BuildingRepresentation::new).collect(toList())).orElseGet(Collections::emptyList);
        this.totalCount = buildingSelection.totalCount();
    }
}
