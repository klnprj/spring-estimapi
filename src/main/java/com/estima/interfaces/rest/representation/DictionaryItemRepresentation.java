package com.estima.interfaces.rest.representation;

import com.estima.domain.DictionaryItem;
import lombok.Getter;

@Getter
public class DictionaryItemRepresentation {

    private Long id;
    private String title;
    private String contactName;
    private String contactPosition;
    private String phone;
    private String name;
    private DictionaryRepresentation dictionary;

    public DictionaryItemRepresentation(DictionaryItem item) {
        this.id = item.id();
        this.title = item.title();
        this.contactName = item.contactName();
        this.contactPosition = item.contactPosition();
        this.phone = item.phone();
        this.name = item.title(); // fixme: remove
        this.dictionary = new DictionaryRepresentation(item.dictionary());
    }
}
