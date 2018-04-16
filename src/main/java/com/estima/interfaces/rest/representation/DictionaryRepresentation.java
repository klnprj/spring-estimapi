package com.estima.interfaces.rest.representation;

import com.estima.domain.Dictionary;
import lombok.Getter;

@Getter
public class DictionaryRepresentation {
    private String key;
    private String name;

    public DictionaryRepresentation(Dictionary dictionary) {
        this.key = dictionary.getKey();
        this.name = dictionary.getName();
    }
}
