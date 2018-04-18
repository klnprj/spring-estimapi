package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "building")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_sequence")
    @SequenceGenerator(name="building_id_sequence", sequenceName = "building_id_sequence")
    private Long id;

    private String name;
    private String address;
    private String location;

    @OneToOne
    @JoinColumn(name = "author")
    private User author;

    private String description;

    @OneToOne
    @JoinColumn(name = "client_id")
    private DictionaryItem client;

    @OneToOne
    @JoinColumn(name = "project_id")
    // fixme: судя по данным всегда NULL, можно убрать?
    private DictionaryItem project;

//    private String status;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date latestPositionDateUpdated;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date earliestPositionDateCreated;
//    private Collection positionsDealers;
//
//    private Collection positions;
//    private Collection contacts;

}
