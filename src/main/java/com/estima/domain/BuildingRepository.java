package com.estima.domain;

import java.util.Collection;
import java.util.Optional;

public interface BuildingRepository {
    Optional<Building> get(Long id);
    Collection<Building> asList();
}
