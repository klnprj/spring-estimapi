package com.estima.interfaces.rest.representation;

import lombok.Getter;

@Getter
public class BuildingContactRepresentation {

    private Long id;
    private BuildingRefRepresentation building;
    private ContactRepresentation contact;
    private String info;
}
