package com.project.emissionsapi.service;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.model.SensorData;
import com.project.emissionsapi.repositories.Co2LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Co2LevelService {

    @Autowired
    private Co2LevelRepository co2LevelRepository;
    @Autowired
    private Co2LevelService co2LevelService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private CityAdminService cityAdminService;

    public MessageResponse save(Co2Level co2Level) {
        boolean exist = co2LevelRepository.existsById((co2Level.getId()));
        if (exist) {
            return new MessageResponse(false, "Not Success", "Existing");
        }
        co2LevelRepository.save(co2Level);
        return new MessageResponse(true, "Success", "The request has been processed successfully");
    }

    public MessageResponse saveSensorData(SensorData sensorData) {
        City city = cityService.findByName(sensorData.getCityName());
        District district = districtService.findByCityAndName(city, sensorData.getDistrictName());
        Co2Level co2Level = new Co2Level(sensorData.getLevel(), sensorData.getTimestamp());
        if (district != null && city != null) {
            co2Level.setDistrict(district);
        } else {
            return new MessageResponse(false, "Error", "Invalid city or district name please enter an existing city and district name");
        }
        return save(co2Level);
    }

    public MessageResponse updateSensorData(Long id, SensorData sensorData) {
        Co2Level co2Level = findById(id);
        City city = cityService.findByName(sensorData.getCityName());
        District district = districtService.findByCityAndName(city, sensorData.getDistrictName());
        co2Level.setDistrict(district);
        co2Level.setLevel(sensorData.getLevel());
        co2Level.setTimestamp(sensorData.getTimestamp());
        if (district != null && city != null) {
            co2Level.setDistrict(district);
        } else {
            return new MessageResponse(false, "Error", "Invalid city or district name please enter an existing city and district name");
        }
        return update(co2Level);
    }

    public MessageResponse update(Co2Level co2Level) {
        co2LevelRepository.save(co2Level);
        return new MessageResponse(true, "Success", "The update request has been processed successfully");
    }

    public MessageResponse delete(Long id) {
        co2LevelRepository.deleteById(id);
        return new MessageResponse(true, "Success", "The delete request has been processed successfully");
    }

    public Co2Level findById(Long id) {
        return co2LevelRepository.findById(id).orElse(null);
    }

    public Co2Level findByDistrict(District district) {
        return co2LevelRepository.findByDistrict(district);
    }

    public List<Co2Level> findAllByCurrentCity() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CityAdmin loggedInUser = (CityAdmin) cityAdminService.loadUserByUsername(username);
        City city = cityService.findByName(loggedInUser.getCity().getName());
        return districtService.findByCity(city).stream().filter(district -> district.getCity().getName().equals(city.getName())).map(District::getCo2Levels).flatMap(List::stream).collect(java.util.stream.Collectors.toList());
    }

    public List<Co2Level> findAll() {
        return co2LevelRepository.findAll();
    }

}