package com.estima.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> get(UserId userId);

    List<User> asList();
}
