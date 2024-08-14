package com.springtuto.Mappers;

import com.springtuto.POJO.Constructor;
import com.springtuto.DTOs.ConstructorDTO;
import com.springtuto.POJO.Driver;
import com.springtuto.Requests.ConstructorRequest;
import com.springtuto.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConstructorMapper implements Function<Constructor, ConstructorDTO> {

    private final DriverService driverService;

    @Autowired
    public ConstructorMapper(DriverService driverService){
        this.driverService = driverService;
    }

    public Constructor toConstructor(ConstructorRequest request){
        try {
            if (request == null){
                throw new IllegalStateException("Request cannot be null");
            }else{
                Constructor constructor = new Constructor();
                constructor.setName(request.getName());
                constructor.setNationality(request.getNationality());
                constructor.setTeamPrincipal(request.getTeamPrincipal());

                Set<Driver> teamLineup = new HashSet<>();

                // Ensure the drivers array is not null
                if (request.getDrivers() != null) {
                    for (String driverName : request.getDrivers()) {
                        Driver driver = driverService.getDriverByName(driverName);
                        if (driver == null) {
                            throw new IllegalStateException("Driver not found: " + driverName);
                        }

                        //Check if the driver is already associated with another constructor
                        if (driver.getCurrentTeam() != null) {
                            throw new IllegalStateException("Driver " + driverName + " is already part of another team.");
                        }

                        teamLineup.add(driver);
                        driver.setCurrentTeam(constructor);
                    }
                }

                constructor.setDriverLineUp(teamLineup);

                return constructor;
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ConstructorDTO apply(Constructor constructor) {
        return new ConstructorDTO(
                constructor.getId(),
                Optional.of(constructor.getName()).orElse(""),
                Optional.of(constructor.getNationality()).orElse(""),
                Optional.of(constructor.getTeamPrincipal()).orElse(""),
                Optional.of(constructor.getDriverLineUp()
                        .stream()
                        .map(Driver::getLastName)
                        .collect(Collectors.toList())).orElse(null)
        );
    }
}
