package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public MessageResponse save(City city){
        boolean exist = cityRepository.existsById((city.getId()));
        if (exist) {
            return new MessageResponse(false,"Not Success","Existing");

        }
        cityRepository.save(city);
        return new MessageResponse(true, "Success", "Backend responded save ok");

    }

    public MessageResponse update(City city){

        cityRepository.save(city);
        return new MessageResponse(true, "Success", "Backend responded update  ok");

    }

    public MessageResponse delete(Long id) {
        cityRepository.deleteById(id);
        return new MessageResponse(true, "Success", "Backend responded delete ok");

    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> findAll() {
        return cityRepository.findAll();

    }

}