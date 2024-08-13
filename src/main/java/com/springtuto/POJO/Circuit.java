package com.springtuto.POJO;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Table
public class Circuit {
    @Id
    @SequenceGenerator(
            name = "circuits_id_sequence",
            sequenceName = "circuits_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "circuits_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "Text",
            length = 32
    )
    private String name;
    @Column(
            name = "country",
            nullable = false,
            columnDefinition = "Text",
            length = 32
    )
    private String country;
    @Column(
            name = "lapLength",
            nullable = false
    )
    private Double lapLength;
    @Transient
    @Column(
            name = "Laps",
            nullable = false
    )
    private Integer nbLaps;

    @OneToMany(mappedBy = "circuit")
    private List<Race> races;

    public Circuit() {
    }

    public Circuit(Long id, String name, String country, Double lapLength) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lapLength = lapLength;
    }

    public Circuit(String name, String country, Double lapLength) {
        this.name = name;
        this.country = country;
        this.lapLength = lapLength;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLapLength() {
        return lapLength;
    }

    public void setLapLength(Double lapLength) {
        this.lapLength = lapLength;
    }

    public Integer getNbLaps() {
        return (int) Math.ceil(305 / lapLength);
    }

    public void setNbLaps(Integer nbLaps) {
        this.nbLaps = nbLaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circuit circuit = (Circuit) o;
        return Objects.equals(id, circuit.id) && Objects.equals(name, circuit.name) && Objects.equals(country, circuit.country) && Objects.equals(lapLength, circuit.lapLength) && Objects.equals(nbLaps, circuit.nbLaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, lapLength, nbLaps);
    }
}
