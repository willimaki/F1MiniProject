package com.springtuto.Services;

import com.springtuto.DTOs.CircuitDTO;
import com.springtuto.Mappers.CircuitMapper;
import com.springtuto.POJO.Circuit;
import com.springtuto.Repositories.CircuitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CircuitServiceTest {

    @Mock
    private CircuitRepository circuitRepository;
    @Mock
    private CircuitMapper circuitMapper;

    private CircuitService underTest;

    @BeforeEach
    void setUp(){
        underTest = new CircuitService(circuitRepository, circuitMapper);
    }

    @Test
    void canGetAllCircuits() {
        //when
        underTest.tryGetAllCircuits();
        //then
        verify(circuitRepository).findAll();
    }

    @Test
    void canAddCircuit() {
        //given
        Circuit circuit = new Circuit(
                "Emilia Romagna",
                "Italy",
                4.909
                );
        //when
        underTest.addCircuit(circuit);

        //then
        ArgumentCaptor<Circuit> circuitArgumentCaptor = ArgumentCaptor.forClass(Circuit.class);
        verify(circuitRepository).save(circuitArgumentCaptor.capture());

        Circuit capturedCircuit = circuitArgumentCaptor.getValue();

        assertThat(capturedCircuit).isEqualTo(circuit);
    }

    @Test
    void willReturnConflictWhenCircuitExists() {
        // given
        Circuit circuit = new Circuit(
                "Emilia Romagna",
                "Italy",
                4.909
        );

        // Mock the repository to throw an exception when saving the circuit
        when(circuitRepository.save(any(Circuit.class))).thenThrow(new RuntimeException("Database error"));

        // when
        ResponseEntity<String> response = underTest.addCircuit(circuit);

        // then
        // Verify the status is INTERNAL_SERVER_ERROR
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        // Verify the response body contains the correct error message
        assertThat(response.getBody()).isEqualTo("An error occurred");

    }
    @Test
    void willThrowWhenCircuitExists() {
        //given
        Circuit circuit = new Circuit(
                "Monza",
                "Italy",
                5.793
        );
        // Mock the repository to throw an exception when saving
        when(circuitRepository.save(any(Circuit.class))).thenThrow(new RuntimeException("Database error"));

        // when
        ResponseEntity<String> response = underTest.addCircuit(circuit);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isEqualTo("Circuit already exixts");
    }

    @Test
    void updateCircuitWithValidDataProvided() {
        // Given
        Long circuitId = 1L;
        Circuit circuit = new Circuit(circuitId, "Old Name", "Old Country", 3.5);
        when(circuitRepository.findById(circuitId)).thenReturn(Optional.of(circuit));

        String newName = "New Name";
        String newCountry = "New Country";
        Double newLapLength = 4.5;

        // When
        underTest.updateCircuit(circuitId, newName, newCountry, newLapLength);

        // Then
        assertEquals(newName, circuit.getName());
        assertEquals(newCountry, circuit.getCountry());
        assertEquals(newLapLength, circuit.getLapLength());
        verify(circuitRepository).findById(circuitId);
    }

    @Test
    void updateCircuitWithSameData() {
        // Given
        Long circuitId = 1L;
        Circuit circuit = new Circuit(circuitId, "Same Name", "Same Country", 4.4);
        when(circuitRepository.findById(circuitId)).thenReturn(Optional.of(circuit));

        String sameName = "Same Name";
        String sameCountry = "Same Country";
        Double sameLapLength = 4.4;

        // When
        underTest.updateCircuit(circuitId, sameName, sameCountry, sameLapLength);

        // Then
        assertEquals("Same Name", circuit.getName());
        assertEquals("Same Country", circuit.getCountry());
        assertEquals(4.4, circuit.getLapLength());
        verify(circuitRepository).findById(circuitId);
    }

    @Test
    //Wont pass because of the catch block in CircuitService
    void updateCircuitWhenCircuitIsNotFound() {
        // Given
        Long circuitId = 1L;
        when(circuitRepository.findById(circuitId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            underTest.updateCircuit(circuitId, "New Name", "New Country", 4.5);
        });

        // Then
        assertEquals("Circuit n°" + circuitId + "is not found", exception.getMessage());
        verify(circuitRepository).findById(circuitId);
    }

    @Test
    void updateCircuitwithNullValues() {
        // Given
        Long circuitId = 1L;
        Circuit circuit = new Circuit(circuitId, "Old Name", "Old Country", 4.4);
        when(circuitRepository.findById(circuitId)).thenReturn(Optional.of(circuit));

        // When
        underTest.updateCircuit(circuitId, null, "", -1.0);

        // Then
        assertEquals("Old Name", circuit.getName());  // Name should not change
        assertEquals("Old Country", circuit.getCountry());  // Country should not change
        assertEquals(4.4, circuit.getLapLength());  // Lap length should not change
        verify(circuitRepository).findById(circuitId);
    }

    @Test
    void deleteCircuitById_whenCircuitExists() {
        // Given
        Long circuitId = 1L;
        when(circuitRepository.existsById(circuitId)).thenReturn(true);

        // When
        underTest.deleteCircuitById(circuitId);

        // Then
        verify(circuitRepository, times(1)).deleteById(circuitId);
    }

    @Test
        //Wont pass because of the catch block in CircuitService
    void deleteCircuitById_whenCircuitDoesNotExist() {
        // Given
        Long circuitId = 1L;
        when(circuitRepository.existsById(circuitId)).thenReturn(false);

        // When
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            underTest.deleteCircuitById(circuitId);
        });

        // Then
        assertEquals("Circuit n°" + circuitId + "is not found", exception.getMessage());
        verify(circuitRepository, never()).deleteById(circuitId);
    }

    @Test
    void tryGetCircuitById() {
        // given
        Long circuitId = 1L;
        Circuit test = new Circuit(
                circuitId,
                "Silverstone",
                "UK",
                5.891
        );
        when(circuitRepository.findById(circuitId)).thenReturn(Optional.of(test));
        when(circuitMapper.apply(any(Circuit.class))).thenAnswer(invocation -> {
            Circuit circuit = invocation.getArgument(0);
            return new CircuitDTO(circuit.getId(), circuit.getName(), circuit.getCountry(), circuit.getLapLength());
        });

        // when
        Optional<CircuitDTO> foundCircuit = underTest.tryGetCircuitById(circuitId);

        // then
        verify(circuitRepository).findById(circuitId);
        assertThat(foundCircuit).isPresent();

        // Create the expected CircuitDTO for comparison
        CircuitDTO expectedCircuitDTO = new CircuitDTO(
                circuitId,
                "Silverstone",
                "UK",
                5.891
        );

        // Compare the result with the expected CircuitDTO
        assertThat(foundCircuit.get()).isEqualTo(expectedCircuitDTO);
    }

    @Test
    void tryGetCircuitsByCountry() {
        // given
        String country = "Italy";
        List<Circuit> circuits = List.of(
                new Circuit(1L, "Monza", "Italy", 5.793),
                new Circuit(2L, "Imola", "Italy", 4.909),
                new Circuit(3L, "Silverstone", "UK", 5.891)
        );

        // Mock the repository to return the list of circuits
        when(circuitRepository.findAll()).thenReturn(circuits);

        // Mock the mapper to map Circuits to CircuitDTOs
        when(circuitMapper.apply(any(Circuit.class))).thenAnswer(invocation -> {
            Circuit circuit = invocation.getArgument(0);
            return new CircuitDTO(circuit.getId(), circuit.getName(), circuit.getCountry(), circuit.getLapLength());
        });

        // when
        Optional<List<CircuitDTO>> result = underTest.tryGetCircuitsByCountry(country);

        // then
        // Verify the repository was called to retrieve all circuits
        verify(circuitRepository).findAll();

        // Verify the mapper was called for each circuit in Italy
        verify(circuitMapper, times(2)).apply(any(Circuit.class));

        // Assert that the result contains the correct circuits
        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(2);

        // Verify that the circuits are correctly filtered by country
        List<String> circuitNames = result.get().stream()
                .map(CircuitDTO::name)
                .collect(Collectors.toList());
        assertThat(circuitNames).containsExactlyInAnyOrder("Monza", "Imola");
    }

    @Test
    void tryGetCircuitsByCountryReturnsEmptyWhenNoCircuitsFound() {
        // given
        String country = "Italy";
        List<Circuit> circuits = List.of();

        // Mock the repository to return an empty list
        when(circuitRepository.findAll()).thenReturn(circuits);

        // when
        Optional<List<CircuitDTO>> result = underTest.tryGetCircuitsByCountry(country);

        // then
        // Verify the repository was called
        verify(circuitRepository).findAll();

        // Assert that the result is empty
        assertThat(result).isEmpty();
    }



    @Test
    void getCircuitByName() {
        //given
        String name = "Emilia Romagna";
        List<Circuit> circuitList = List.of(
                new Circuit(1L, "Emilia Romagna", "Italy", 4.505),
                new Circuit(2L, "Circuit de Spa Francorchamps","Belgium",7.475)
        );
        when(circuitRepository.findAll()).thenReturn(circuitList);

        //when
        Circuit result = underTest.getCircuitByName(name);

        // then
        // On vérifie que la méthode `findAll()` du repository a bien été appelée
        verify(circuitRepository).findAll();


        // On vérifie que le résultat n'est pas null
        assertThat(result).isNotNull();

        // On vérifie que le circuit retourné a bien les propriétés attendues
        assertThat(result.getName()).isEqualTo("Emilia Romagna");
        assertThat(result.getCountry()).isEqualTo("Italy");
        assertThat(result.getLapLength()).isEqualTo(4.505);


    }
}