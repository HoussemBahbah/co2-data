package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityAdminService cityAdminService;

    public MessageResponse save(City city) {
        boolean exist = cityRepository.existsById((city.getId()));
        if (exist) {
            return new MessageResponse(false, "Not Success", "Existing");
        }
        cityRepository.save(city);
        return new MessageResponse(true, "Success", "The request has been processed successfully");
    }

    public MessageResponse update(City city) {
        cityRepository.save(city);
        return new MessageResponse(true, "Success", "The Update request has been processed successfully");
    }

    public MessageResponse delete(Long id) {
        cityRepository.deleteById(id);
        return new MessageResponse(true, "Success", "The Delete request has been processed successfully");
    }

    public City getCurrentCity() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CityAdmin loggedInUser = (CityAdmin) cityAdminService.loadUserByUsername(username);
        return findByName(loggedInUser.getCity().getName());
    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City findByName(String name) {
        return cityRepository.findByNameIgnoreCase(name);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

}