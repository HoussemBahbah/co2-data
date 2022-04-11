package com.project.emissionsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Co2Level {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String level;
    private String timestamp;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="district_id")
    private District district;


    public Co2Level(String level, String timestamp) {
        this.level = level;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Co2Level{" +
                "level='" + level + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public Co2Level() {
    }
    //    @JsonIgnore
//    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
   // private List<Client> clients;


}