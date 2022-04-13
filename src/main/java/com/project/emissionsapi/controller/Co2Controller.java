package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.model.SensorData;
import com.project.emissionsapi.service.CityService;
import com.project.emissionsapi.service.Co2LevelService;
import com.project.emissionsapi.service.DistrictService;
import com.project.emissionsapi.service.UserDetailService;
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
    private UserDetailService userDetailService;

    @Autowired
    private DistrictService districtService;

//        @PostMapping
//        public MessageResponse save(@RequestBody Co2Level co2Level) {
//            return co2LevelService.save(co2Level);
//        }

    @PutMapping
    public MessageResponse update(@RequestBody Co2Level co2Level) {
        return co2LevelService.update(co2Level);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return co2LevelService.delete(id);
    }

    @GetMapping("/{all}")
    public List<Co2Level> findAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetail loggedInUser = (UserDetail) userDetailService.loadUserByUsername(username);

        City city = cityService.findByName(loggedInUser.getCity().getName());
        return districtService.findByCity(city).stream().filter(district->district.getCity().getName().equals(city.getName())).map(District::getCo2Levels).flatMap(List::stream).collect(java.util.stream.Collectors.toList());

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Co2Level> findByCity(@RequestParam(value = "districtName") String districtName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetail loggedInUser = (UserDetail) userDetailService.loadUserByUsername(username);
        City city = cityService.findByName(loggedInUser.getCity().getName());
        try {
            return districtService.findByCityAndName(city, districtName).getCo2Levels();
         } catch (RuntimeException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "District Not Found please enter an existing district name existing in the city " +city.getName());
        }

    }


    @GetMapping("/{id}")
    public Co2Level findById(@PathVariable Long id) {
        return co2LevelService.findById(id);
    }

    @PostMapping
    public MessageResponse save(@RequestBody SensorData sensorData) {

            City city = cityService.findByName(sensorData.getCityName());
            District district = districtService.findByCityAndName(city, sensorData.getDistrictName());
            Co2Level co2Level = new Co2Level(sensorData.getLevel(), sensorData.getTimestamp());
            if(district!=null&&city!=null) {
                co2Level.setDistrict(district);
            }
            else {
                return new MessageResponse(false, "Error","Invalid city or district name please enter an existing city and district name");
            }
            return co2LevelService.save(co2Level);
    }

}
