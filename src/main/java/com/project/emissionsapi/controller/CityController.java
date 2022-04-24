package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.service.CityService;
import com.project.emissionsapi.service.CityAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/city")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityAdminService cityAdminService;


    @GetMapping
    public City findAdminCity() {
        return cityService.getCurrentCity();
    }

    @GetMapping("/all")
    public List<City> findAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/")
    public City findByCityName(@RequestParam(value = "cityName") String cityName) {
        return cityService.findByName(cityName);
    }


}
