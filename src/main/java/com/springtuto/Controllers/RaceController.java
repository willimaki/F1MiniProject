package com.springtuto.Controllers;

import com.springtuto.Mappers.RaceMapper;
import com.springtuto.DTOs.RaceDTO;
import com.springtuto.Requests.RaceRequest;
import com.springtuto.Services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/f1project/v1/races")
public class RaceController {
    private final RaceService raceService;
    private final RaceMapper raceMapper;

    @Autowired
    public RaceController(RaceService raceService, RaceMapper raceMapper) {
        this.raceService = raceService;
        this.raceMapper = raceMapper;
    }

    @PostMapping
    public void createRace(@RequestBody RaceRequest race){
        raceService.createRace(raceMapper.toRace(race));
    }

    @GetMapping
    public Optional<List<RaceDTO>> tryGetRaces(@RequestParam(required = false) Integer year) {
        if (year != null) {
            return raceService.tryGetAllRacesByYear(year);
        } else {
            return raceService.tryGetAllRaces();
        }
    }

    @GetMapping("/{raceId}")
    public Optional<RaceDTO> tryGetRaceById(@PathVariable Long raceId){
        return raceService.tryGetRaceById(raceId);
    }

}
