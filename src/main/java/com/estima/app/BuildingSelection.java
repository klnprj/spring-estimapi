package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.ex.BuildingMissingException;

import java.util.Collection;

public interface BuildingSelection {

    Building get(Long id) throws BuildingMissingException;
    Collection<Building> query();
}
