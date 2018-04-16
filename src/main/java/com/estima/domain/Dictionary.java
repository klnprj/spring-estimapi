package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dictionary")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_id_sequence")
    @SequenceGenerator(name="dictionary_id_sequence", sequenceName = "dictionary_id_sequence")
    private Long id;

    private String key;

    private String name;
}
