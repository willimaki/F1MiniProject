package com.springtuto.Services;

import com.springtuto.DTOs.ConstructorDTO;

import com.springtuto.Mappers.ConstructorMapper;

import com.springtuto.POJO.Constructor;
import com.springtuto.Repositories.ConstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstructorService {
    private final ConstructorRepository constructorRepository;
    private final ConstructorMapper constructorMapper;


    @Autowired
    public ConstructorService(ConstructorRepository constructorRepository, ConstructorMapper constructorMapper) {
        this.constructorRepository = constructorRepository;
        this.constructorMapper = constructorMapper;
    }


    public Optional<ConstructorDTO> tryGetConstructorById(Long constructorId){
        try {
            return constructorRepository.findById(constructorId)
                    .map(constructorMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerConstructor(Constructor newConstructor) {
        try {
            if(constructorRepository.findAll()
                    .stream()
                    .noneMatch(constructor -> constructor.getName().equalsIgnoreCase(newConstructor.getName()))){
                    constructorRepository.save(newConstructor);
            }else {
                throw new IllegalStateException(newConstructor.getName() + " Already exists");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public List<ConstructorDTO> tryGetAllConstructors(){
        try {
            return constructorRepository.findAll()
                    .stream()
                    .map(constructorMapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Constructor getConstructorByName(String name){
        try{
            return constructorRepository.findAll()
                    .stream()
                    .filter(constructor -> constructor.getName().contains(name))
                    .findFirst()
                    .orElse(null);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void tryDeleteConstuctorById(Long constructorId){
        try {
            if(constructorRepository.existsById(constructorId)){
                constructorRepository.deleteById(constructorId);
            }else{
                throw new IllegalStateException("Constructor with n°" + constructorId + "not found");
            }
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void tryUpdateConstructorById(Long constructorId, String name, String nationality, String teamPrincipal, List<String> teamLineUp) {
        try {
            Constructor constructor = constructorRepository.getReferenceById(constructorId);

            if (constructor == null) {
                throw new IllegalStateException("Constructor with ID " + constructorId + " not found");
            }

            if (isValidString(name)) {
                constructor.setName(name);
            }

            if (isValidString(nationality)) {
                constructor.setNationality(nationality);
            }

            if (isValidString(teamPrincipal)) {
                constructor.setTeamPrincipal(teamPrincipal);
            }

            if (teamLineUp != null && teamLineUp.size() == 2) {

            } else {
                throw new IllegalArgumentException("Team line-up must have exactly 2 drivers");
            }

        } catch (EntityNotFoundException e) {
            System.err.println("Constructor not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
