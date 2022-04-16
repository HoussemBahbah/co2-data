package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.UserDetailRepository;
import com.project.emissionsapi.service.CityService;
import com.project.emissionsapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/city")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private UserDetailService userDetailService;


    @GetMapping
    public City findAdminCity() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetail loggedInUser = (UserDetail) userDetailService.loadUserByUsername(username);
        return cityService.findByName(loggedInUser.getCity().getName());
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
