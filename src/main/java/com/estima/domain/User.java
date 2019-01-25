package com.estima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class User {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "username"))
    private UserId id;

    @Column(name = "username", insertable = false, updatable = false)
    private String name;

    @Column(name = "username", insertable = false, updatable = false)
    private String email;

    private boolean enabled;

    public User(String email) {
        this.id = UserId.of(email);
        this.name = email;
        this.email = email;
        this.enabled = true;
    }
}
