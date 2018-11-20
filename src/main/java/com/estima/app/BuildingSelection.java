package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.UserId;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.interfaces.rest.request.BuildingCreateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface BuildingSelection {

    Building get(Long id) throws BuildingMissingException;
    Collection<Building> query();

    Building create(@NotNull UserId userId, @NotNull @Valid BuildingCreateRequest request);

    Building update(@NotNull Long id, @NotNull @Valid BuildingCreateRequest request) throws BuildingMissingException;
}
