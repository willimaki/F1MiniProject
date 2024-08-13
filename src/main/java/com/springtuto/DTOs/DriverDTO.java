package com.springtuto.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.List;

public record DriverDTO (
     Long id,
     String lastName,
     String firstName,
     String nationality,
     Integer number,
     @JsonFormat(pattern = "dd/MM/yyyy")
     LocalDate birthDate,
     Integer age,
     String currentTeam,
     List<String> raceNames
){}


