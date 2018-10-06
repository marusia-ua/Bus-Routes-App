package com.marusia.bus.routes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
    @JsonProperty("dep_sid")
    private Integer departureStationId;

    @JsonProperty("arr_sid")
    private Integer arrivalStationId;

    @JsonProperty("direct_bus_route")
    private Boolean isConnected;
}
