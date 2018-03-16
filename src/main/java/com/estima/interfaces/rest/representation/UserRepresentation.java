package com.estima.interfaces.rest.representation;

import com.estima.domain.User;
import lombok.Getter;

@Getter
public class UserRepresentation {

    private String id;
    private String name;
    private String email;
    private boolean enabled;


    public UserRepresentation(User user) {
        this.id = user.getId().asString();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
