package com.project.emissionsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    private List<Co2Level> Co2Levels;


    public District(String districtName) {
        this.districtName = districtName;
    }

    public District() {
    }


}