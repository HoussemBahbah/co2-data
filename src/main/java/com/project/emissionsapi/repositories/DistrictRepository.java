package com.project.emissionsapi.repositories;

import com.project.emissionsapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DistrictRepository extends JpaRepository<District, Long> {

        boolean existsByDistrictName(String string);
        District findByDistrictName(String string);
}
