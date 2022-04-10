package com.project.emissionsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String co2Level;
    private String district;

    public City(String name, String co2Level, String district) {
        this.name = name;
        this.co2Level = co2Level;
        this.district = district;
    }

    public City() {
    }
    //    @JsonIgnore
//    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
   // private List<Client> clients;


}