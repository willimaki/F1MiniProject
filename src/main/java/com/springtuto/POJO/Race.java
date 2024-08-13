package com.springtuto.POJO;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Race {
    @Id
    @SequenceGenerator(
            name = "race_id_sequence",
            sequenceName = "race_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "race_id_sequence"
    )
    private Long id;

    @Transient
    private String name;

    @ManyToOne
    @JoinColumn(name = "circuit_Id" )
    private Circuit circuit;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "pole_driver_id")
    private Driver pole;

    @ManyToOne
    @JoinColumn(name = "fastest_lap_driver_id")
    private Driver fastestLap;

    @ManyToOne
    @JoinColumn(name = "race_winner_driver_id")
    private Driver raceWinner;

    @ManyToMany
    @JoinTable(
            name = "race_podium",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private Set<Driver> podium;

    @ManyToMany
    @JoinTable(
            name = "race_f1drivers",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")

    )
    private Set<Driver> drivers;

    private RaceType raceType;

    public Race(Long id, Circuit circuit, LocalDate date, Driver pole, Driver fastestLap, Driver raceWinner, Set<Driver> podium, RaceType raceType) {
        this.id = id;
        this.circuit = circuit;
        this.date = date;
        this.pole = pole;
        this.fastestLap = fastestLap;
        this.raceWinner = raceWinner;
        this.podium = podium;
    }

    public Race(Circuit circuit, LocalDate date, Driver pole, Driver fastestLap, Driver raceWinner, Set<Driver> podium, RaceType raceType) {
        this.circuit = circuit;
        this.date = date;
        this.pole = pole;
        this.fastestLap = fastestLap;
        this.raceWinner = raceWinner;
        this.podium = podium;
    }

    public Race() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return circuit.getName() + " " + getDate().getYear();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Driver getPole() {
        return pole;
    }

    public void setPole(Driver pole) {
        this.pole = pole;
    }

    public Driver getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(Driver fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Driver getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(Driver raceWinner) {
        this.raceWinner = raceWinner;
    }

    public Set<Driver> getPodium() {
        return podium;
    }

    public void setPodium(Set<Driver> podium) {
        this.podium = podium;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id) && Objects.equals(circuit, race.circuit) && Objects.equals(date, race.date) && Objects.equals(pole, race.pole) && Objects.equals(fastestLap, race.fastestLap) && Objects.equals(raceWinner, race.raceWinner) && Objects.equals(podium, race.podium) && Objects.equals(drivers, race.drivers) && raceType == race.raceType;
    }

    public void createRace(Race race) {
    }
}
