package com.project.emissionsapi.repositories;

import com.project.emissionsapi.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City, Long> {

        boolean existsByName(String string);
        City findByName(String string);
}
