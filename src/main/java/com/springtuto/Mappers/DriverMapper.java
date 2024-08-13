package com.springtuto.Mappers;

import com.springtuto.POJO.Circuit;
import com.springtuto.POJO.Constructor;
import com.springtuto.POJO.Driver;
import com.springtuto.DTOs.DriverDTO;
import com.springtuto.POJO.Race;
import com.springtuto.Requests.DriverRequest;
import com.springtuto.Services.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DriverMapper implements Function<Driver, DriverDTO> {

    private final ConstructorService constructorService;

    @Autowired
    public DriverMapper(@Lazy ConstructorService constructorService){
        this.constructorService = constructorService;
    }
    public Driver toDriver(DriverRequest request){
        try{
            if(request == null){
                throw new IllegalArgumentException("Request cannot be null");
            }else{
                Driver driver = new Driver();

                driver.setFirstName(request.getFirstName());
                driver.setLastName(request.getLastName());
                driver.setNationality(request.getNationality());
                driver.setNumber(request.getNumber());
                driver.setBirthDate(request.getBirthDate());
                driver.setCurrentTeam(constructorService.getConstructorByName(request.getCurrentTeam()));

                return driver;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public DriverDTO apply(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                Optional.ofNullable(driver.getLastName()).orElse(""),
                Optional.ofNullable(driver.getFirstName()).orElse(""),
                Optional.ofNullable(driver.getNationality()).orElse(""),
                Optional.ofNullable(driver.getNumber()).orElse(0),
                Optional.of(driver.getBirthDate()).orElse(null),
                Optional.of(driver.getAge()).orElse(0),

                Optional.ofNullable(driver.getCurrentTeam())
                        .map(Constructor::getName)
                        .orElse(""),
                Optional.ofNullable(driver.getRaces())
                        .map(races -> races.stream()
                                .map(Race::getCircuit)
                                .map(Circuit::getName)
                                .collect(Collectors.toList()))
                        .orElse(Collections.emptyList())
        );
    }
}
