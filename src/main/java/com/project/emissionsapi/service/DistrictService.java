package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public MessageResponse save(District district){
        boolean exist = districtRepository.existsById((district.getId()));
        if (exist) {
            return new MessageResponse(false,"Not Success","Existing");

        }
        districtRepository.save(district);
        return new MessageResponse(true, "Success", "Backend responded save ok");

    }

    public MessageResponse update(District district){

        districtRepository.save(district);
        return new MessageResponse(true, "Success", "Backend responded update  ok");

    }

    public MessageResponse delete(Long id) {
        districtRepository.deleteById(id);
        return new MessageResponse(true, "Success", "Backend responded delete ok");

    }

    public District findById(Long id) {
        return districtRepository.findById(id).orElse(null);
    }

    public District findByName(String name) {
        return districtRepository.findByDistrictName(name);
    }

    public List<District> findAll() {
        return districtRepository.findAll();

    }

}