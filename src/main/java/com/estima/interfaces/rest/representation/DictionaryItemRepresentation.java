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
        this.id = item.getId();
        this.title = item.getTitle();
        this.contactName = item.getContactName();
        this.contactPosition = item.getContactPosition();
        this.phone = item.getPhone();
        this.name = item.getTitle(); // fixme: remove
        this.dictionary = new DictionaryRepresentation(item.getDictionary());
    }
}
