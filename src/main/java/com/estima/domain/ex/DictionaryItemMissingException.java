package com.estima.domain.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DictionaryItemMissingException extends Exception {

    public DictionaryItemMissingException(Long dictionaryId, Long id) {
        super("Не найдено значение [" + id + "] в справочнике [" + dictionaryId + "]");
    }
}
