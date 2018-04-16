package com.estima.domain;

import java.util.Optional;

public interface DictionaryItemRepository {
    Optional<DictionaryItem> get(Long dictionaryId, Long id);
}
