package com.project.emissionsapi.repositories;

import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Co2LevelRepository extends JpaRepository<Co2Level, Long> {

    Co2Level findByDistrict(District district);
}
