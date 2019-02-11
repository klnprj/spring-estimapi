package com.estima.domain.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PositionMissingException extends Exception {

    public PositionMissingException(Long id) {
        super("Позиция с идентификатором [" + id + "] не найдена!");
    }
}
