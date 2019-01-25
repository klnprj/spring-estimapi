package com.estima.interfaces.rest.request;

import com.estima.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UserCreateRequest {

    @NotEmpty
    private final String name;

    public UserCreateRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public User asUser() {
        return new User(this.name);
    }
}
