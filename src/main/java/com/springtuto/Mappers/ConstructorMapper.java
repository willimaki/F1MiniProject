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
                for (int i = 0; i < request.getDrivers().length ; i++) {
                    teamLineup.add(driverService.getDriverByName(request.getDrivers()[i]));
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
