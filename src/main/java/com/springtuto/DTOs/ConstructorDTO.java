package com.springtuto.DTOs;



import java.util.List;

public record ConstructorDTO (
     Long id,
     String name,
     String nationality,
     String teamPrincipal,
     List<String> driversNames
)
{

}

