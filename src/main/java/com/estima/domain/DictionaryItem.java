package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dictionaryitem")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DictionaryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_item_id_sequence")
    @SequenceGenerator(name="dictionary_item_id_sequence", sequenceName = "dictionary_item_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", insertable = false, updatable = false)
    private Dictionary dictionary;

    private String title;

    @Column(name = "contactname")
    private String contactName;

    @Column(name = "contactposition")
    private String contactPosition;

    private String phone;
}
