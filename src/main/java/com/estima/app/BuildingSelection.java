package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.BuildingNotFoundException;

import java.util.Collection;

public interface BuildingSelection {

    Building get(Long id) throws BuildingNotFoundException;
    Collection<Building> query();
}
