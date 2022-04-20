package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CityAdminService cityAdminService;
    @Autowired
    private CityService cityService;

    public MessageResponse createDistrict(String districtName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CityAdmin loggedInUser = (CityAdmin) cityAdminService.loadUserByUsername(username);
        City city = cityService.findByName(loggedInUser.getCity().getName());
        District district = new District(districtName);
        district.setCity(city);
        city.addDistrict(district);
        cityService.save(city);
        return save(district);
    }

    public MessageResponse save(District district) {
        boolean exist = districtRepository.existsById((district.getId()));
        if (exist) {
            return new MessageResponse(false, "Not Success", "Existing");

        }
        districtRepository.save(district);
        return new MessageResponse(true, "Success", "Backend responded save ok");

    }

    public MessageResponse update(District district) {

        districtRepository.save(district);
        return new MessageResponse(true, "Success", "Backend responded update  ok");

    }

    public MessageResponse delete(Long id) {
        districtRepository.deleteById(id);
        return new MessageResponse(true, "Success", "Backend responded delete ok");

    }

    public List<District> getCurrentCityDistricts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CityAdmin loggedInUser = (CityAdmin) cityAdminService.loadUserByUsername(username);
        City city = cityService.findByName(loggedInUser.getCity().getName());
        return city.getDistricts();
    }

    public District findById(Long id) {
        return districtRepository.findById(id).orElse(null);
    }

    public District findByCityAndName(City city, String name) {
        return districtRepository.findByCityAndDistrictNameIgnoreCase(city, name);
    }

    public List<District> findByCity(City city) {
        return districtRepository.findByCity(city);
    }

    public District findByName(String name) {
        return districtRepository.findByDistrictNameIgnoreCase(name);
    }


    public List<District> findAll() {
        return districtRepository.findAll();

    }

}