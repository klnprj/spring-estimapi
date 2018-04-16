package com.estima.app;

import com.estima.domain.DictionaryItem;
import com.estima.domain.ex.DictionaryItemMissingException;

public interface DictionaryItemSelection {
    DictionaryItem get(Long dictionaryId, Long id) throws DictionaryItemMissingException;
}
