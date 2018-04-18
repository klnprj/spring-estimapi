package com.estima.interfaces.rest.representation;

import com.estima.domain.DictionaryItem;
import lombok.Getter;

@Getter
public class ClientRepresentation extends DictionaryItemRepresentation {

    public ClientRepresentation(DictionaryItem item) {
        super(item);
    }
}
