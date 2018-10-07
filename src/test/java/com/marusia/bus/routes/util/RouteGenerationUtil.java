package com.marusia.bus.routes.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RouteGenerationUtil {

    private static int routeLength = 100;
    private static int numberOfRoutes = 10000;
    private static int numberOfStations = 1000;

    public static void generateRoutesFile(String fileName) throws IOException {
        String headLine = numberOfRoutes + "\n";
        Files.write(
                Paths.get(fileName),
                headLine.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        for (int i = 0; i < numberOfRoutes; ++i) {
            String route = getRouteLine(i);
            appendLine(fileName, route);
        }
    }

    private static void appendLine(String fileName, String line) throws IOException {
        Files.write(
                Paths.get(fileName),
                line.getBytes(),
                StandardOpenOption.APPEND);
    }

    private static String getRouteLine(int routeId) {
        StringBuilder line = new StringBuilder();
        line.append(routeId);
        for (int i = 0; i < routeLength; ++i) {
            int stationId = (int) (Math.random() * numberOfStations);
            line.append(" ").append(stationId);
        }
        line.append("\n");
        return line.toString();
    }

    /**
     * Manual test method for generating routes files.
     *
     * @throws IOException
     */
//    @Test
    public void generateRouteFile() throws IOException {
        String fileName = "data/RoutesFile.txt";
        RouteGenerationUtil.generateRoutesFile(fileName);
    }

}
