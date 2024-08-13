package com.springtuto.Requests;

public class CircuitRequest {

    private String name;
    private String country;
    private double lapLength;

    public CircuitRequest() {
    }

    public CircuitRequest(String name, String country, double lapLength) {
        this.name = name;
        this.country = country;
        this.lapLength = lapLength;
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

    public double getLapLength() {
        return lapLength;
    }

    public void setLapLength(double lapLength) {
        this.lapLength = lapLength;
    }
}


