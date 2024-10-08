package com.springtuto.Controllers;

import com.springtuto.Mappers.CircuitMapper;
import com.springtuto.DTOs.CircuitDTO;
import com.springtuto.Requests.CircuitRequest;
import com.springtuto.Services.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/f1project/v1/circuits")
public class CircuitController {
    private final CircuitService circuitService;
    private final CircuitMapper circuitMapper;

    @Autowired
    public CircuitController(CircuitService circuitService, CircuitMapper circuitMapper) {
        this.circuitService = circuitService;
        this.circuitMapper = circuitMapper;
    }

    @GetMapping()
    public Optional<List<CircuitDTO>> tryGetAllCircuits(){
        return circuitService.tryGetAllCircuits();
    }


    // Using ResponseEntity for testing purpose - can be interesting to have full control on HTML response
    @GetMapping("/{circuitId}")
    public ResponseEntity<CircuitDTO> tryGetCircuitsById(@PathVariable Long circuitId){
        return circuitService.tryGetCircuitById(circuitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/country")
    public ResponseEntity<List<CircuitDTO>> tryGetCircuitsByCountry(@RequestParam(value="country") String country){
        return circuitService.tryGetCircuitsByCountry(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> registerCircuit(@RequestBody CircuitRequest circuit){
        return circuitService.addCircuit(circuitMapper.toCircuit(circuit));
    }

    @PutMapping("/{circuitId}")
    public void updateCircuit(@PathVariable("circuitId") Long circuitId,
                              @RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "country", required = false) String country,
                              @RequestParam(value ="lapLength", required = false) Double lapLength){
        circuitService.updateCircuit(circuitId, name, country, lapLength);
    }

    @DeleteMapping("/{circuitId}")
    public void deleteCircuitById(@PathVariable("circuitId") Long circuitId){
        circuitService.deleteCircuitById(circuitId);
    }
}
