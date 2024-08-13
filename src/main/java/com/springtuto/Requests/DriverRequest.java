package com.springtuto.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DriverRequest {
    private String lastName;
    private String firstName;
    private String nationality;
    private Integer number;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String currentTeam;
    private String[] races;

    public DriverRequest() {
    }

    public DriverRequest(String lastName, String firstName, String nationality, Integer number, LocalDate birthDate, String currentTeam, String[] races) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nationality = nationality;
        this.number = number;
        this.birthDate = birthDate;
        this.currentTeam = currentTeam;
        this.races = races;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String[] getRaces() {
        return races;
    }

    public void setRaces(String[] races) {
        this.races = races;
    }
}
