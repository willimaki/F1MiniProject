package com.springtuto.POJO;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Constructor {
    @Id
    @SequenceGenerator(
            name = "f1constructors_id_sequence",
            sequenceName = "f1constructors_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "f1constructors_id_sequence"
    )
    private Long id;
    private String name;
    private String nationality;
    private String teamPrincipal;
    @OneToMany(mappedBy = "currentTeam", fetch = FetchType.LAZY )
    private Set<Driver> driverLineUp;

    public Constructor() {
    }

    public Constructor(Long id, String name, String nationality, String teamPrincipal, Set<Driver> driverLineUp) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.teamPrincipal = teamPrincipal;
        this.driverLineUp = driverLineUp;
    }

    public Constructor(String name, String nationality, String teamPrincipal, Set<Driver> driverLineUp) {
        this.name = name;
        this.nationality = nationality;
        this.teamPrincipal = teamPrincipal;
        this.driverLineUp = driverLineUp;
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

    public Set<Driver> getDriverLineUp() {
        return driverLineUp;
    }

    public void setDriverLineUp(Set<Driver> driverLineUp) {
        this.driverLineUp = driverLineUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constructor that = (Constructor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(nationality, that.nationality) && Objects.equals(teamPrincipal, that.teamPrincipal) && Objects.equals(driverLineUp, that.driverLineUp);
    }
}
