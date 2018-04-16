package com.estima.app;

import com.estima.domain.Building;
import com.estima.domain.ex.BuildingMissingException;
import com.estima.domain.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
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
}
