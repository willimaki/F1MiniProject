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
                Set<Driver> podium = new HashSet<>();

                race.setCircuit(circuitService.getCircuitByName(request.getCircuit()));
                race.setDate(request.getDate());
                race.setPole(driverService.getDriverByName(request.getPole()));
                race.setFastestLap(driverService.getDriverByName(request.getFastestLap()));
                race.setRaceWinner(driverService.getDriverByName(request.getRaceWinner()));

                for (int i = 0; i < request.getPodium().length; i++) {
                    podium.add(driverService.getDriverByName(request.getPodium()[i]));
                }

                race.setPodium(podium);
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
                Optional.of(race.getCircuit().toString()).orElse(""),
                Optional.of(race.getName()).orElse(""),
                Optional.of(race.getDate()).orElse(null),
                Optional.of(race.getPole().toString()).orElse(""),
                Optional.of(race.getFastestLap().toString()).orElse(""),
                Optional.of(race.getRaceWinner().toString()).orElse(""),
                Optional.of(race.getPodium()
                        .stream()
                        .map(Driver::getLastName)
                        .collect(Collectors.toList())).orElse(null),
                race.getRaceType()
        );
    }
}
