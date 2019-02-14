package com.estima.interfaces.rest.representation;

import com.estima.domain.Position;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Getter
public class PositionRepresentation {

    private final long id;
    private final BuildingRefRepresentation building;
    private final Date dateCreated;
    private final LocalDate dateShipped;
    private final DealerRepresentation dealer;
    private final String contactName;
    private final String type;
    private final String spec;
    private final String grossPrice;
    private final String total;
    private final String status;
    private final Integer dealerPrice;
    private final Integer quantity;

    public PositionRepresentation(Position position) {
        this.id = position.id();
        this.building = new BuildingRefRepresentation(position.building());
        this.dateCreated = position.dateCreated();
        this.dateShipped = Optional.ofNullable(position.dateShipped()).map(d -> d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).orElse(null);
        this.dealer = new DealerRepresentation(position.dealer());
        this.contactName = position.contactName();
        this.type = position.type();
        this.spec = position.spec();
        this.grossPrice = position.grossPrice();
        this.total = position.total();
        this.status = position.status();
        this.dealerPrice = position.dealerPrice();
        this.quantity = position.quantity();
    }
}
