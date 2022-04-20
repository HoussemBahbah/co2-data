package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.service.CityService;
import com.project.emissionsapi.service.DistrictService;
import com.project.emissionsapi.service.CityAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/district")
@CrossOrigin("*")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityAdminService cityAdminService;


    @PostMapping
    public MessageResponse save(@RequestParam(value = "districtName") String districtName) {

        return districtService.createDistrict(districtName);
    }


    @PutMapping
    public MessageResponse update(@RequestParam(value = "districtName") String districtName, @RequestParam(value = "newDistrictName") String newDistrictName) {
        District district = districtService.findByName(districtName);
        district.setDistrictName(newDistrictName);
        return districtService.update(district);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return districtService.delete(id);
    }

    @GetMapping("/all")
    public List<District> findAll() {
        return districtService.findAll();
    }


    @GetMapping("/cityDistricts")
    public List<District> currentCityDistricts() {
        return districtService.getCurrentCityDistricts();
    }


    @GetMapping("/")
    public District findByDistrictName(@RequestParam(value = "districtName") String districtName) {
        return districtService.findByName(districtName);
    }

}
