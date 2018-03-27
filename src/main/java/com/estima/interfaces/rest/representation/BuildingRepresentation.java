package com.estima.interfaces.rest.representation;

import com.estima.domain.Building;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Getter
public class BuildingRepresentation {
    private Long id;
    private String name;
    private String address;
    private String location;
    private String description;
    private ClientRepresentation client;
    private ProjectRepresentation project;
    private UserRepresentation author;
    private Date dateCreated;
    private Date lastUpdated;
    private Collection<DealerRepresentation> dealers;
    private Collection<BuildingContactRepresentation> contacts;
    private String status;

    public BuildingRepresentation(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.address = building.getAddress();
        this.description = building.getDescription();
//        this.author = new UserRepresentation(building.getAuthor());
    }
}
