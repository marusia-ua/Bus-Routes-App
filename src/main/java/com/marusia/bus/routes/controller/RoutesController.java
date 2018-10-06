package com.marusia.bus.routes.controller;

import com.marusia.bus.routes.domain.RouteResponse;
import com.marusia.bus.routes.service.RoutesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class RoutesController {

    @Autowired
    private RoutesService service;

    @GetMapping(value = "/direct", produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteResponse checkConnection(@RequestParam("dep_sid") int depStationId,
                                         @RequestParam("arr_sid") int arrStationId) {
        log.info("Checking for direct connection between stations {} and {}", depStationId, arrStationId);
        boolean isConnected = service.checkConnection(depStationId, arrStationId);
        return new RouteResponse(depStationId, arrStationId, isConnected);
    }
}
