package com.springtuto.Services;

import com.springtuto.DTOs.DriverDTO;
import com.springtuto.Mappers.DriverMapper;
import com.springtuto.POJO.Driver;
import com.springtuto.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Autowired
    public DriverService(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    public Optional<List<DriverDTO>> tryGetAllDrivers(){
        try{
            List<Driver> driverList = driverRepository.findAll();
            if (driverList.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(driverList.stream()
                    .map(driverMapper)
                    .collect(Collectors.toList())
            );

        }catch (Exception e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DriverDTO> tryGetDriverById(Long driverId){
        try{
            Driver driver = driverRepository.getReferenceById(driverId);
            return Optional.of(driver)
                    .map(driverMapper);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public void createDriver(Driver driver){
        try {
            driverRepository.save(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<DriverDTO> tryGetDriverByName(String name) {
        try{
            List<Driver> driverList = driverRepository.findAll();

            if (driverList.isEmpty()) {
                return Optional.empty();
            }

            // Stream to find the driver by last name or first name
            return driverList.stream()
                    .filter(driver -> driver.getLastName().equalsIgnoreCase(name) || driver.getFirstName().equalsIgnoreCase(name))
                    .findFirst()
                    .map(driverMapper);

        } catch (Exception e) {
            // Catch unexpected exceptions and log the error
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Driver getDriverByName(String name) {
        try {
            List<Driver> driverList = driverRepository.findAll();

            if (driverList.isEmpty()) {
                return null;
            }

            // Stream to find the driver by last name or first name
            return driverList.stream()
                    .filter(driver -> driver.getLastName().equalsIgnoreCase(name) || driver.getFirstName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            // Catch unexpected exceptions and log the error
            System.err.println(e.getMessage());
            return null;
        }
    }
}
