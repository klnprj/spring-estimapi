package com.estima.interfaces.rest.request;

import com.estima.domain.UserId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Getter
@Accessors(fluent = true)
public class BuildingSearchRequest {
    private final int offset;
    private final int max;
    private final String sort;
    private final String order;
    private final String query;
    private final Set<UserId> authorsIds;
    private final Set<String> statuses;
    private final LocalDate lastUpdatedFrom;
    private final Set<Long> dealersIds;

    @JsonCreator
    public BuildingSearchRequest(@JsonProperty("offset") Integer offset,
                                 @JsonProperty("max") Integer max,
                                 @JsonProperty("sort") String sort,
                                 @JsonProperty("order") String order,
                                 @JsonProperty("q") String q,
                                 @JsonProperty("authorsIds") Set<UserId> authorsIds,
                                 @JsonProperty("dealersIds") Set<Long> dealersIds,
                                 @JsonProperty("statuses") Set<String> statuses,
                                 @JsonProperty("lastUpdatedFrom") LocalDate lastUpdatedFrom) {
        this.offset = Optional.ofNullable(offset).orElse(0);
        this.max = Optional.ofNullable(max).orElse(100);
        this.sort = Optional.ofNullable(sort).orElse("id"); // todo: creation order by date?
        this.order = Optional.ofNullable(order).orElse("asc");
        this.query = q;
        this.authorsIds = Optional.ofNullable(authorsIds).orElseGet(Collections::emptySet);
        this.dealersIds = Optional.ofNullable(dealersIds).orElseGet(Collections::emptySet);
        this.statuses = Optional.ofNullable(statuses).orElseGet(Collections::emptySet);
        this.lastUpdatedFrom = lastUpdatedFrom;
    }
}