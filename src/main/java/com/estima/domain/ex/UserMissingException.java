package com.estima.domain.ex;

import com.estima.domain.UserId;

public class UserMissingException extends Exception {
    private String userId;

    public UserMissingException(UserId userId) {
        super("Не найден пользователь с идентификатором [" + userId.asString() + "]");
        this.userId = userId.asString();
    }
}
