package com.project.emissionsapi.repositories;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findByCity(City city);

    District findByCityAndDistrictNameIgnoreCase(City city, String name);

    boolean existsByDistrictNameIgnoreCase(String string);

    District findByDistrictNameIgnoreCase(String string);
}
