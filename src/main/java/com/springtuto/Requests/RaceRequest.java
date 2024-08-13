package com.springtuto.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springtuto.POJO.RaceType;

import java.time.LocalDate;

public class RaceRequest {
    private String circuit;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String pole;
    private String fastestLap;
    private String raceWinner;
    private String[] podium;
    private RaceType raceType;

    public RaceRequest() {
    }

    public RaceRequest(String circuit, LocalDate date, String pole, String fastestLap, String raceWinner, String[] podium, RaceType raceType) {
        this.circuit = circuit;
        this.date = date;
        this.pole = pole;
        this.fastestLap = fastestLap;
        this.raceWinner = raceWinner;
        this.podium = podium;
        this.raceType = raceType;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPole() {
        return pole;
    }

    public void setPole(String pole) {
        this.pole = pole;
    }

    public String getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(String fastestLap) {
        this.fastestLap = fastestLap;
    }

    public String getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(String raceWinner) {
        this.raceWinner = raceWinner;
    }

    public String[] getPodium() {
        return podium;
    }

    public void setPodium(String[] podium) {
        this.podium = podium;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }
}
