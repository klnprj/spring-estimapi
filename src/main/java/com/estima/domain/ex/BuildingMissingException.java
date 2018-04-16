package com.estima.domain.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BuildingMissingException extends Exception {

    public BuildingMissingException(Long id) {
        super("Объект с идентификатором [" + id + "] не найден!");
    }
}
