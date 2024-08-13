package com.springtuto.Configurations;

import com.springtuto.POJO.Circuit;
import com.springtuto.Repositories.CircuitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CircuitConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(CircuitRepository circuitRepository){
        return args -> {
            Circuit monza = new Circuit(
                    "Monza", "Italy",5.793
            );
            Circuit emiliaRomagna = new Circuit(
                    "Emila Romagna", "Italy",4.909);

            circuitRepository.saveAll(List.of(monza, emiliaRomagna));
        };


    }
}
