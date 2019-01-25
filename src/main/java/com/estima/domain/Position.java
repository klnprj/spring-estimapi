package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "position")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Accessors(fluent = true)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_id_sequence")
    @SequenceGenerator(name="position_id_sequence", sequenceName = "position_id_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    private Building building;

    @Column(name = "building_id")
    private Long buildingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealer_id", insertable = false, updatable = false)
    private DictionaryItem dealer;

    @Column(name = "dealer_id")
    private Long dealerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreated")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdated")
    private Date lastUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateshipped")
    private Date dateShipped;

    @Column(name = "contactname")
    private String contactName;
    private String type;
    private String spec;

    @Column(name = "grossprice")
    private String grossPrice;
    private String total;
    private String status;

    @Column(name = "dealerprice")
    private Integer dealerPrice;
    private Integer quantity;

    public Position(long buildingId, long dealerId, String contactName, String type, String spec, String grossPrice,
                    String total, String status, Integer dealerPrice, Integer quantity) {
        Objects.requireNonNull(contactName);
        this.buildingId = buildingId;
        this.dealerId = dealerId;
        this.dateCreated = Date.from(Instant.now());
        this.contactName = contactName;
        this.type = type;
        this.spec = spec;
        this.grossPrice = grossPrice;
        this.total = total;
        this.status = status;
        this.dealerPrice = dealerPrice;
        this.quantity = quantity;
    }
}
