package com.project.emissionsapi.model;


import lombok.Data;

@Data
public class SensorData {

    private String level;
    private String timestamp;
    private String cityName;
    private String districtName;

}
