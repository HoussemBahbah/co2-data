package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.Co2LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Co2LevelService {

    @Autowired
    private Co2LevelRepository co2LevelRepository;

    public MessageResponse save(Co2Level co2Level) {
        boolean exist = co2LevelRepository.existsById((co2Level.getId()));
        if (exist) {
            return new MessageResponse(false, "Not Success", "Existing");
        }
        co2LevelRepository.save(co2Level);
        return new MessageResponse(true, "Success", "Backend responded save ok");

    }

    public MessageResponse update(Co2Level co2Level) {
        co2LevelRepository.save(co2Level);
        return new MessageResponse(true, "Success", "Backend responded update  ok");
    }

    public MessageResponse delete(Long id) {
        co2LevelRepository.deleteById(id);
        return new MessageResponse(true, "Success", "Backend responded delete ok");

    }

    public Co2Level findById(Long id) {
        return co2LevelRepository.findById(id).orElse(null);
    }

    public Co2Level findByDistrict(District district) {
        return co2LevelRepository.findByDistrict(district);
    }

    public List<Co2Level> findAll() {
        return co2LevelRepository.findAll();
    }

}