package com.springtuto.Requests;

public class ConstructorRequest {
    private String name;
    private String nationality;
    private String teamPrincipal;
    private String[] drivers;

    public ConstructorRequest() {
    }

    public ConstructorRequest(String name, String nationality, String teamPrincipal, String[] drivers) {
        this.name = name;
        this.nationality = nationality;
        this.teamPrincipal = teamPrincipal;
        this.drivers = drivers;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeamPrincipal() {
        return teamPrincipal;
    }

    public void setTeamPrincipal(String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    public String[] getDrivers() {
        return drivers;
    }

    public void setDrivers(String[] drivers) {
        this.drivers = drivers;
    }
}
