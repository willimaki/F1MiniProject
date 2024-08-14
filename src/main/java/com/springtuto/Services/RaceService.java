package com.springtuto.Services;

import com.springtuto.DTOs.RaceDTO;
import com.springtuto.Mappers.RaceMapper;
import com.springtuto.POJO.Race;
import com.springtuto.Repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;

    @Autowired
    public RaceService(RaceRepository raceRepository, RaceMapper raceMapper) {
        this.raceRepository = raceRepository;
        this.raceMapper = raceMapper;
    }

    public Optional<RaceDTO> tryGetRaceById(Long raceId) {
        try {
            return raceRepository.findById(raceId)
                    .map(raceMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<RaceDTO>> tryGetAllRaces() {
        try {
            return Optional.of(raceRepository.findAll()
                    .stream()
                    .map(raceMapper)
                    .collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createRace(Race race) {
        try {
            raceRepository.save(race);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<RaceDTO>> tryGetAllRacesByYear(int year) {
        try {
            List<Race> allRaces = raceRepository.findAll();

            if (allRaces.isEmpty()) {
                throw new IllegalStateException("No Races found");
            }

            return Optional.of(allRaces
                    .stream()
                    .filter(race -> race.getDate().getYear() == year)
                    .map(raceMapper)
                    .collect(Collectors.toList())
            );
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }


}
