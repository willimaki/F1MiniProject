package com.springtuto.Controllers;

import com.springtuto.Mappers.DriverMapper;
import com.springtuto.DTOs.DriverDTO;
import com.springtuto.Requests.DriverRequest;
import com.springtuto.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="api/f1project/v1/drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;

    @Autowired
    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @PostMapping
    public void createDriver(@RequestBody DriverRequest driver){
        driverService.createDriver(driverMapper.toDriver(driver));
    }

    @GetMapping
    public Optional<List<DriverDTO>> tryGetAllDrivers(){
        return driverService.tryGetAllDrivers();
    }

    @GetMapping("/{driverId}")
    public Optional<DriverDTO> tryGetDriverById(@PathVariable Long driverId){
        return driverService.tryGetDriverById(driverId);
    }

    @GetMapping("/name")
    public Optional<DriverDTO> tryGetDriverByName(@RequestParam String name){
        return driverService.tryGetDriverByName(name);
    }

}
