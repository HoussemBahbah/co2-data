package com.project.emissionsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Co2Level {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String level;
    private @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime timestamp;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;


    public Co2Level(String level, LocalDateTime timestamp) {
        this.level = level;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Co2Level{" +
                "level='" + level + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", city='" + district.getCity().getName() + '\'' +
                ", district='" + district.getDistrictName() + '\'' +
                '}';
    }

    public Co2Level() {
    }
}