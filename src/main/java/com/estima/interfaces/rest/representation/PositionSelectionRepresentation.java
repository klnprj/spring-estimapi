package com.estima.interfaces.rest.representation;

import com.estima.domain.Position;
import lombok.Getter;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Getter
public class PositionSelectionRepresentation {

    private Collection<PositionRepresentation> positionList;

    public PositionSelectionRepresentation(Collection<Position> positions) {
        this.positionList = positions.stream().map(PositionRepresentation::new).collect(toList());
    }
}
