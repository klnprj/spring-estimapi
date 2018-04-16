package com.estima.app;

import com.estima.domain.DictionaryItem;
import com.estima.domain.DictionaryItemRepository;
import com.estima.domain.ex.DictionaryItemMissingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DictionaryItemSelectionImpl implements DictionaryItemSelection {

    private DictionaryItemRepository dictionaryItems;

    @Override
    public DictionaryItem get(Long dictionaryId, Long id) throws DictionaryItemMissingException {
        return dictionaryItems.get(dictionaryId, id).orElseThrow(() -> new DictionaryItemMissingException(dictionaryId, id));
    }
}
