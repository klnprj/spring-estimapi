package com.estima.domain;

import com.estima.interfaces.rest.request.BuildingLocateRequest;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BuildingRepository {
    Optional<Building> get(Long id);

    @Transactional(readOnly = true)
    BuildingSelection locate(BuildingLocateRequest request);

    void add(Building building);

    void update(Building building);

    BuildingSelection query(BuildingSearchRequest request);
}
