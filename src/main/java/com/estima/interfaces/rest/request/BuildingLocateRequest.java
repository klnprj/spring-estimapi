package com.estima.interfaces.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@Accessors(fluent = true)
public class BuildingLocateRequest {

//    @NotNull
//    @Valid
    private final LatLng latLng;

//    @NotNull
    private final Integer radius;

    @JsonCreator
    public BuildingLocateRequest(@JsonProperty("latlng") String latlng, // fixme: bind to List<Double>
                                 @JsonProperty("radius") Integer radius) {
        this.latLng = Optional.ofNullable(latlng).map(s -> Arrays.stream(s.split(",")).map(Double::parseDouble).collect(toList())).map(l -> new LatLng(l.get(0), l.get(1))).orElse(null);
        this.radius = radius;
    }

    public boolean isEmpty() {
        return latLng == null || latLng.isEmpty() || radius == null;
    }

    @Getter
    @Accessors(fluent = true)
    @AllArgsConstructor
    public static class LatLng {
//        @NotNull
        private final Double lat;
//        @NotNull
        private final Double lng;

        public boolean isEmpty() {
            return lat == null || lng == null;
        }
    }
}
