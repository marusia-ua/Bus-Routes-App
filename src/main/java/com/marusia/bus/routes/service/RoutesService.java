package com.marusia.bus.routes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class RoutesService {

    private Map<Integer, List<Integer>> routes;
    private Map<Integer, Set<Integer>> routesByStation;

    /**
     * Loads routes data from given file.
     *
     * @param routesFile
     * @throws FileNotFoundException
     */
    public void loadRoutes(String routesFile) throws FileNotFoundException {
        log.info("Loading Routes from file {}", routesFile);

        routesByStation = new HashMap<>();
        routes = new HashMap<>();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(routesFile)));
        int routesNumber = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 1; i <= routesNumber; ++i) {
            loadRoute(scanner.nextLine());
            logLoadingProgress(i);
        }

        log.info("Loaded {} routes", routes.size());
        log.info("Total amount of bus stations: {}", routesByStation.size());
    }

    private void logLoadingProgress(int i) {
        if (i % 1000 == 0) {
            log.info("Loaded {} routes", i);
        }
    }

    private void loadRoute(String line) {
        String[] routeData = line.split(" ");
        int routeId = Integer.parseInt(routeData[0]);
        List<Integer> stationIds = new ArrayList<>(routeData.length - 1);
        for (int i = 1; i < routeData.length; ++i) {
            int stationId = Integer.parseInt(routeData[i]);
            stationIds.add(stationId);
            routesByStation.computeIfAbsent(stationId, routes -> new HashSet<>()).add(routeId);
        }
        routes.put(routeId, stationIds);
    }

    /**
     * Checks that given stations are connected via direct bus route.
     *
     * @param depStationId departure station id
     * @param arrStationId arrival station id
     * @return true if stations are connected via direct bus route
     */
    public boolean checkConnection(int depStationId, int arrStationId) {
        if (depStationId == arrStationId) {
            return true;
        }
        Set<Integer> departureRoutes = routesByStation.get(depStationId);
        Set<Integer> arrivalRoutes = routesByStation.get(arrStationId);
        if (isEmpty(departureRoutes) || isEmpty(arrivalRoutes)) {
            return false;
        }

        Set<Integer> intersection = new HashSet<>(departureRoutes);
        intersection.retainAll(arrivalRoutes);

        for (Integer routId : intersection) {
            if (isConnected(routId, depStationId, arrStationId)) {
                return true;
            }
        }

        return false;
    }

    private boolean isConnected(Integer routId, int depStationId, int arrStationId) {
        List<Integer> stationIds = routes.get(routId);
        return stationIds.indexOf(depStationId) < stationIds.indexOf(arrStationId);
    }
}
