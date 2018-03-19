package com.estima.domain;

public class BuildingNotFoundException extends Exception {

    public BuildingNotFoundException(Long id) {
        super("Объект с идентификатором [" + id + "] не найден!");
    }
}
