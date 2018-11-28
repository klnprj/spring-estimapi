package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "position")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_id_sequence")
    @SequenceGenerator(name="position_id_sequence", sequenceName = "position_id_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

//    private DictionaryItem dealer;

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
}
