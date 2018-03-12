package com.estima.domain;

public class UserNotFoundException extends Exception {
    private String userId;

    public UserNotFoundException(UserId userId) {
        super("Не найден пользователь с идентификатором [" + userId.asString() + "]");
        this.userId = userId.asString();
    }
}
