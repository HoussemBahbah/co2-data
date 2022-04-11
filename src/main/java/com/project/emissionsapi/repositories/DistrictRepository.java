package com.project.emissionsapi.repositories;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DistrictRepository extends JpaRepository<District, Long> {

        List<District> findByCity(City city);
        District findByCityAndDistrictName(City city, String name);
        boolean existsByDistrictName(String string);
        District findByDistrictName(String string);
}
