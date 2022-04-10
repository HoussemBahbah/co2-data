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


    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<UserDetail> userDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<District> districts;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }



}