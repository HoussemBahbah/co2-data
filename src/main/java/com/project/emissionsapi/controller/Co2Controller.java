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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
            return co2LevelService.findAll();
        }

        @RequestMapping(method = RequestMethod.GET)
        public List<Co2Level> findByCity(@RequestParam(value="districtName") String districtName) {
            String username =  SecurityContextHolder.getContext().getAuthentication().getName();
            UserDetail loggedInUser = (UserDetail) userDetailService.loadUserByUsername(username);
            City city=cityService.findByName(loggedInUser.getCity().getName());
            System.out.println(city.getName());
            System.out.println(districtName);
            System.out.println(districtService.findAll());
            return districtService.findByCityAndName(city,districtName).getCo2Levels();
        }


        @GetMapping("/{id}")
        public Co2Level findById(@PathVariable Long id) {
            return co2LevelService.findById(id);
        }


    @PostMapping
    public MessageResponse save(@RequestBody SensorData sensorData) {

        City city=cityService.findByName(sensorData.getCityName());
        District district=districtService.findByCityAndName(city,sensorData.getDistrictName());
        Co2Level co2Level=new Co2Level();
        co2Level.setDistrict(district);
        return co2LevelService.save(co2Level);

    }

}
