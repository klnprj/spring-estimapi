package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "building")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_sequence")
    @SequenceGenerator(name="building_id_sequence", sequenceName = "building_id_sequence")
    private Long id;

    @Version
    private Long version;

    private String name;
    private String address;
    private String location;

    @OneToOne
    @JoinColumn(name = "author", insertable = false, updatable = false)
    private User author;

    @AttributeOverride(name = "value", column = @Column(name = "author"))
    private UserId authorId;

    private String description;

    @OneToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private DictionaryItem client;

    @Column(name = "client_id")
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    // fixme: судя по данным всегда NULL, можно убрать?
    private DictionaryItem project;

    @Column(name = "project_id")
    private Long projectId;

    public Building(UserId authorId, String name, String address, String location, String description, String status, long clientId, Long projectId) {
        Objects.requireNonNull(authorId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
        Objects.requireNonNull(location);
        this.authorId = authorId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
//        this.status = status;
        this.clientId = clientId;
        this.projectId = projectId;
    }

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
