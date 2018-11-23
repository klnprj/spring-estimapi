package com.estima.app;

import com.estima.domain.BuildingRepository;
import com.estima.domain.BuildingSelection;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

public interface QueryBuilding {
    BuildingSelection query(@NotNull BuildingSearchRequest request);

    // default implementation
    @Service
    @AllArgsConstructor
    class Default implements QueryBuilding {

        private final BuildingRepository buildings;

        @Override
        public BuildingSelection query(@NotNull BuildingSearchRequest request) {
            return buildings.query(request);
        }
    }
}
