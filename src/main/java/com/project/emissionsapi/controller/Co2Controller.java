package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.model.SensorData;
import com.project.emissionsapi.service.CityService;
import com.project.emissionsapi.service.Co2LevelService;
import com.project.emissionsapi.service.DistrictService;
import com.project.emissionsapi.service.CityAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("api/co2Level")
@CrossOrigin("*")
public class Co2Controller {

    @Autowired
    private Co2LevelService co2LevelService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityAdminService cityAdminService;

    @Autowired
    private DistrictService districtService;

    @PutMapping
    public MessageResponse update(@RequestParam(value = "id") Long id, @RequestBody SensorData sensorData) {
        return co2LevelService.updateSensorData(id, sensorData);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return co2LevelService.delete(id);
    }

    @GetMapping("/all")
    public List<Co2Level> findAllCo2LevelsOfTheCurrentCity() {
        return co2LevelService.findAllByCurrentCity();

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Co2Level> findByDistrict(@RequestParam(value = "districtName") String districtName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CityAdmin loggedInUser = (CityAdmin) cityAdminService.loadUserByUsername(username);
        City city = cityService.findByName(loggedInUser.getCity().getName());
        try {
            return districtService.findByCityAndName(city, districtName).getCo2Levels();
        } catch (RuntimeException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "District Not Found please enter an existing district name existing in the city " + city.getName());
        }

    }


    @PostMapping
    public MessageResponse save(@RequestBody SensorData sensorData) {

        return co2LevelService.saveSensorData(sensorData);
    }

}
