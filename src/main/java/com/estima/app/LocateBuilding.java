package com.estima.app;

import com.estima.domain.BuildingRepository;
import com.estima.domain.BuildingSelection;
import com.estima.interfaces.rest.request.BuildingLocateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface LocateBuilding {

    BuildingSelection locate(@NotNull @Valid BuildingLocateRequest request);


    // default implementation
    @Service
    @Validated
    @AllArgsConstructor
    class Default implements LocateBuilding {

        private final BuildingRepository buildingRepository;

        @Override
        public BuildingSelection locate(@NotNull @Valid BuildingLocateRequest request) {
            return buildingRepository.locate(request);
        }
    }
}
