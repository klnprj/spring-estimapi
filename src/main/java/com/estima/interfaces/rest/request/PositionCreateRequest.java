package com.estima.interfaces.rest.request;

import com.estima.domain.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Accessors(fluent = true)
public class PositionCreateRequest {

    @NotNull
    private final Long buildingId;

    @NotNull
    private final Long dealerId;

    @NotEmpty
    private final String contactName;

    private final String type;
    private final String spec;
    private final String grossPrice;
    private final String total;
    private final String status;

    private final Date dateShipped;

    @NotNull
    private final Integer dealerPrice;

    @NotNull
    private final Integer quantity;

    public PositionCreateRequest(@JsonProperty("buildingId") Long buildingId,
                                 @JsonProperty("dealerId") Long dealerId,
                                 @JsonProperty("contactName") String contactName,
                                 @JsonProperty("type") String type,
                                 @JsonProperty("spec") String spec,
                                 @JsonProperty("grossPrice") String grossPrice,
                                 @JsonProperty("total") String total,
                                 @JsonProperty("status") String status,
                                 @JsonProperty("dateShipped") Date dateShipped,
                                 @JsonProperty("dealerPrice") Integer dealerPrice,
                                 @JsonProperty("quantity") Integer quantity) {
        this.buildingId = buildingId;
        this.dealerId = dealerId;
        this.contactName = contactName;
        this.type = type;
        this.spec = spec;
        this.grossPrice = grossPrice;
        this.total = total;
        this.status = status;
        this.dateShipped = dateShipped;
        this.dealerPrice = dealerPrice;
        this.quantity = quantity;
    }

    public Position asPosition() {
        return new Position(this.buildingId, this.dealerId, this.contactName, this.type, this.spec, this.grossPrice, this.total, this.status, this.dealerPrice, this.quantity);
    }
}
