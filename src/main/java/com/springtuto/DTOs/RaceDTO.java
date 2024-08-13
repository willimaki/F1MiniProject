package com.springtuto.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springtuto.POJO.Driver;
import com.springtuto.POJO.RaceType;

import java.time.LocalDate;
import java.util.List;

public record RaceDTO (
     Long id,
     String raceName,
     String circuitName,
     @JsonFormat(pattern = "dd/MM/yyyy")
     LocalDate date,
     String poleName,
     String fastestLapName,
     String raceWinner,
     List<String> podiumNames,
     RaceType raceType
){}

