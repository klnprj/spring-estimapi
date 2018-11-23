package com.estima.domain;

import com.estima.interfaces.rest.request.BuildingSearchRequest;

import java.util.Optional;

public interface BuildingRepository {
    Optional<Building> get(Long id);

    void add(Building building);

    void update(Building building);

    BuildingSelection query(BuildingSearchRequest request);
}
