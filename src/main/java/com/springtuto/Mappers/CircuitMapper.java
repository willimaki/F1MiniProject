package com.springtuto.Mappers;

import com.springtuto.POJO.Circuit;
import com.springtuto.DTOs.CircuitDTO;
import com.springtuto.Requests.CircuitRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Component
@Service
public class CircuitMapper implements Function<Circuit, CircuitDTO> {

    /*
    Mappers : Contains methods to map simplify the request query,
    doing all the intelligence to create the object in a simple way
     */
    public Circuit toCircuit(CircuitRequest request){
        try{
            if (request == null){
                throw new IllegalArgumentException("Circuit request cannot be null");
            }else{

                Circuit circuit = new Circuit();
                circuit.setName(request.getName());
                circuit.setCountry(request.getCountry());
                circuit.setLapLength(request.getLapLength());

                return circuit;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public CircuitDTO apply(Circuit circuit) {
        return new CircuitDTO(
                circuit.getId(),
                Optional.of(circuit.getName()).orElse(""),
                Optional.of(circuit.getName()).orElse(""),
                Optional.of(circuit.getLapLength()).orElse(0.0)
        );
    }
}
