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
//    private DictionaryItem client;
//    private DictionaryItem project;
//    @OneToOne
//    @JoinColumn(name = "")
    // todo: как заменить id (long) у старого пользователя на id (varchar) у нового
    @Column(name = "author_id")
    private Long author;
    private String description;
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
