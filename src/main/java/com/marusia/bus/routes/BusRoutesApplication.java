package com.marusia.bus.routes;

import com.marusia.bus.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@SpringBootApplication
public class BusRoutesApplication {

	private static String routesFileName;

	@Autowired
	private RoutesService service;

	public static void main(String[] args) {
		setRoutesFile(args);
		SpringApplication.run(BusRoutesApplication.class, args);
	}

	private static void setRoutesFile(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("Routes file name is not provided as a command line argument");
		}
		routesFileName = args[0];
	}

	@PostConstruct
	private void loadRoutesData() throws FileNotFoundException {
		service.loadRoutes(routesFileName);
	}
}
