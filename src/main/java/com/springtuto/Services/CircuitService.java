package com.springtuto.Services;

import com.springtuto.DTOs.CircuitDTO;
import com.springtuto.Mappers.CircuitMapper;
import com.springtuto.POJO.Circuit;
import com.springtuto.Repositories.CircuitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CircuitService {
    private final CircuitRepository circuitRepository;
    private final CircuitMapper circuitMapper;

    @Autowired
    public CircuitService(CircuitRepository circuitRepository, CircuitMapper circuitMapper) {
        this.circuitRepository = circuitRepository;
        this.circuitMapper = circuitMapper;
    }

    public Optional<List<CircuitDTO>> getAllCircuits(){
        try{
            return Optional.of(circuitRepository.findAll()
                    .stream()
                    .map(circuitMapper)
                    .collect(Collectors.toList()));
        }catch (Exception e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public ResponseEntity<String> addCircuit(Circuit newCircuit) {
        try {
            if (circuitRepository.findAll()
                    .stream()
                    .noneMatch(circuit -> circuit.getName().equals(newCircuit.getName()))) {
                circuitRepository.save(newCircuit);
                return new ResponseEntity<>("Circuit added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Circuit already exists", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void updateCircuit(Long circuitId, String name, String country, Double lapLength){
        try {
            Circuit circuit = circuitRepository.findById(circuitId)
                    .orElseThrow(() -> new IllegalStateException("Circuit n°" + circuitId + "is not found"));

            if(name != null && !name.isEmpty() && !Objects.equals(circuit.getName(),name)){
                circuit.setName(name);
            }
            if(name != null && !country.isEmpty() && !Objects.equals(circuit.getCountry(),country)){
                circuit.setCountry(country);
            }

            if(lapLength != null && lapLength > 0 && !Objects.equals(circuit.getLapLength(),lapLength)){
                circuit.setLapLength(lapLength);
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteCircuitById(Long circuitId) {
        try {
            if(circuitRepository.existsById(circuitId)){
                circuitRepository.deleteById(circuitId);
            }else{
                throw new IllegalStateException("Circuit n°" + circuitId + "is not found");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public Optional<CircuitDTO> tryGetCircuitById(Long circuitId){
        try {
            return circuitRepository.findById(circuitId)
                    .map(circuitMapper);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<CircuitDTO>> tryGetCircuitsByCountry(String country) {
        try {
            List<Circuit> circuitList = circuitRepository.findAll();
            if (circuitList.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(circuitList.stream()
                    .filter(circuit -> Objects.equals(circuit.getCountry().toLowerCase(), country.toLowerCase()))
                    .map(circuitMapper)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Circuit getCircuitByName(String circuitName) {
        try {
            return circuitRepository.findAll()
                    .stream()
                    .filter(circuit -> circuit.getName().equalsIgnoreCase(circuitName))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.err.println("Error finding circuit: " + e.getMessage());
            return null;
        }
    }
}
