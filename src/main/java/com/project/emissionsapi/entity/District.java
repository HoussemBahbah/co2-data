package com.project.emissionsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String districtName;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonBackReference
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    private List<Co2Level> co2Levels;


    public District(String districtName) {
        this.districtName = districtName;
    }

    public District() {
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", districtName='" + districtName + '\'' +
                ", city=" + city +
                ", Co2Levels=" + co2Levels +
                '}';
    }
}