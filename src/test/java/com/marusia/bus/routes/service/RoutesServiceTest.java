package com.marusia.bus.routes.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class RoutesServiceTest {

    @Parameterized.Parameter(value = 0)
    public int depStationId;

    @Parameterized.Parameter(value = 1)
    public int arrStationId;

    @Parameterized.Parameter(value = 2)
    public boolean isConnected;

    private static RoutesService service = new RoutesService();

    @BeforeClass
    public static void setUp() throws FileNotFoundException {
        service.loadRoutes("src/test/resources/data/SmallRoutesFile.txt");
    }

    @Parameterized.Parameters(name = "{index}: testCheckStationsConnected({0},{1}): {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1, true},
                {0, 2, true},
                {0, 4, true},
                {1, 2, true},
                {1, 4, true},
                {3, 4, true},
                {3, 5, true},
                {1, 5, true},
                {3, 1, true},
                {0, 6, true},

                {0, 5, false},
                {1, 0, false},
                {4, 3, false},
                {5, 6, false},
                {3, 2, false},
                {4, 0, false},
                {4, 4, true},
                {4, 6, false},
                {-1, 6, false},
        });
    }

    @Test
    public void testCheckConnection() {
        assertThat(service.checkConnection(depStationId, arrStationId), is(isConnected));
    }

}
