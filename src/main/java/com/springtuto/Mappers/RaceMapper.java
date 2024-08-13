package com.springtuto.Mappers;

import com.springtuto.POJO.Driver;
import com.springtuto.POJO.Race;
import com.springtuto.DTOs.RaceDTO;
import com.springtuto.Requests.RaceRequest;
import com.springtuto.Services.CircuitService;
import com.springtuto.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RaceMapper implements Function<Race, RaceDTO> {
    private final DriverService driverService;
    private final CircuitService circuitService;

    @Autowired
    public RaceMapper (DriverService driverService, CircuitService circuitService){
        this.driverService = driverService;
        this.circuitService = circuitService;
    }

    public Race toRace(RaceRequest request){
        try {
            if(request == null){
                throw new IllegalStateException("Request cannot be null");
            }else{
                Race race = new Race();

                race.setCircuit(circuitService.getCircuitByName(request.getCircuit()));
                race.setDate(request.getDate());

                race.setPole(driverService.getDriverByName(request.getPole()));
                race.setFastestLap(driverService.getDriverByName(request.getFastestLap()));
                race.setRaceWinner(driverService.getDriverByName(request.getRaceWinner()));

                Set<Driver> podium = new HashSet<>();

                if(request.getPodium() != null) {  // Ensure the drivers array is not null
                    for (String driverName : request.getPodium()) {
                        Driver driver = driverService.getDriverByName(driverName);
                        if (driver == null) {
                            throw new IllegalStateException("Driver not found: " + driverName);
                        }
                        podium.add(driver);
                    }
                }
                race.setPodium(podium);
                race.setRaceType(request.getRaceType());

                return race;

            }
        }catch (Exception e){
            System.err.println();
            return null;
        }
    }

    @Override
    public RaceDTO apply(Race race) {
        return new RaceDTO(
                race.getId(),
                Optional.of(race.getName()).orElse(""),
                Optional.of(race.getCircuit().getName()).orElse(""),
                Optional.of(race.getDate()).orElse(null),
                Optional.of(race.getPole().getLastName()).orElse(""),
                Optional.of(race.getFastestLap().getLastName()).orElse(""),
                Optional.of(race.getRaceWinner().getLastName()).orElse(""),
                Optional.of(race.getPodium()
                        .stream()
                        .map(Driver::getLastName)
                        .collect(Collectors.toList())).orElse(null),
                race.getRaceType()
        );
    }
}
