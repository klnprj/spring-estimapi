package com.estima.interfaces.rest;

import com.estima.app.DictionaryItemSelection;
import com.estima.domain.DictionaryItem;
import com.estima.domain.ex.DictionaryItemMissingException;
import com.estima.interfaces.rest.representation.DictionaryItemRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/dictionaries/{dictionaryId}/items")
@AllArgsConstructor
public class DictionaryItemManagementResource {

    private DictionaryItemSelection dictionaryItemSelection;

    @GetMapping("/")
    public ResponseEntity list(@PathVariable("dictionaryId") Long dictionaryId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("dictionaryId") Long dictionaryId, @PathVariable("id") Long id) throws DictionaryItemMissingException {
        DictionaryItem item = dictionaryItemSelection.get(dictionaryId, id);
        return ResponseEntity.ok(new DictionaryItemRepresentation(item));
    }

    @PostMapping("/")
    public ResponseEntity add(@PathVariable("dictionaryId") Long dictionaryId, @RequestBody Map item) {
        return ResponseEntity.created(URI.create("/api/dictionaries/" + dictionaryId + "/items/" + 1)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("dictionaryId") Long dictionaryId, @PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }


}
