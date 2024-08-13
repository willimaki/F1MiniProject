package com.springtuto.POJO;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Driver {
    @Id
    @SequenceGenerator(
            name = "f1drivers_id_sequence",
            sequenceName = "f1drivers_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "f1drivers_id_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;
    @Transient
    private Integer age;
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "constructor_id")
    private Constructor currentTeam;
    @ManyToMany(mappedBy = "drivers")
    private Set<Race> races;

    public Driver() {
    }

    public Driver(Long id, String firstName, String lastName, String nationality, LocalDate birthDate, Integer number, Constructor currentTeam, Set<Race> races) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.age = age;
        this.number = number;
        this.currentTeam = currentTeam;
        this.races = races;
    }

    public Driver(String firstName, String lastName, String nationality, LocalDate birthDate, Integer number, Constructor currentTeam, Set<Race> races) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.age = age;
        this.number = number;
        this.currentTeam = currentTeam;
        this.races = races;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Constructor getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Constructor team) {
        this.currentTeam = team;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(nationality, driver.nationality) && Objects.equals(birthDate, driver.birthDate) && Objects.equals(age, driver.age) && Objects.equals(number, driver.number) && Objects.equals(currentTeam, driver.currentTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationality, birthDate, age, number, currentTeam);
    }
}
