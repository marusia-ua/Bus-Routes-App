# Bus Routes

Application for checking whether a direct bus-route exists between 2 stations

## Getting Started

Start spring-boot application, providing the name of the Routes Data file as a first argument, using command:
```
mvn spring-boot:run -Dspring-boot.run.arguments="data/RoutesFile.txt"
```

Or package the application and then run the jar:
```
mvn package
java -jar target/bus-routes-1.0-SNAPSHOT.jar data/RoutesFile.txt
```
where "data/RoutesFile.txt" is a sample Routes file, you can provide your own file here