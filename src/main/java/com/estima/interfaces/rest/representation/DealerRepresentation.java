package com.estima.interfaces.rest.representation;

import com.estima.domain.DictionaryItem;
import lombok.Getter;

@Getter
public class DealerRepresentation {
    private long id;
    private String title;
    private String contactName;
    private String contactPosition;
    private String phone;
    private String name;
    private DictionaryRepresentation dictionary;

    public DealerRepresentation(DictionaryItem dealer) {
        this.id = dealer.id();
        this.title = dealer.title();
        this.contactName = dealer.contactName();
        this.contactPosition = dealer.contactPosition();
        this.phone = dealer.phone();
        this.name = dealer.title();
        this.dictionary = new DictionaryRepresentation(dealer.dictionary());
    }
}
