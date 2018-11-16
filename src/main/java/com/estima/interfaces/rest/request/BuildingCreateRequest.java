package com.estima.interfaces.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Accessors(fluent = true)
public class BuildingCreateRequest {

    @NotEmpty
    private final String name;
    @NotEmpty
    private final String address;
    @NotEmpty
    private final String location;

    private final String description;
    private final String status;

    @NotNull
    @Valid
    private final DictionaryItemRef client;
    private final DictionaryItemRef project;

    public BuildingCreateRequest(@JsonProperty("name") String name,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("location") String location,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("status") String status,
                                 @JsonProperty("client") DictionaryItemRef client,
                                 @JsonProperty("project") DictionaryItemRef project) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
        this.status = status;
        this.client = client;
        this.project = project;
    }

    public Long clientId() {
        return Optional.ofNullable(client).map(DictionaryItemRef::id).orElse(null);
    }

    public Long projectId() {
        return Optional.ofNullable(project).map(DictionaryItemRef::id).orElse(null);
    }

    @Getter
    @Accessors(fluent = true)
    public static class DictionaryItemRef {
        @NotNull
        private final Long id;

        public DictionaryItemRef(@JsonProperty("id") Long id) {
            this.id = id;
        }
    }
}
