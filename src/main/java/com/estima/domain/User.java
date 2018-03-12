package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "username"))
    private UserId id;

    @Column(name = "username", insertable = false, updatable = false)
    private String name;

    public User(String name) {
        this.id = UserId.of(name);
        this.name = name;
    }
}
