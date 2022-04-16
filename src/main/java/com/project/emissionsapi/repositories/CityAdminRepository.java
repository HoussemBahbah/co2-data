package com.project.emissionsapi.repositories;


import com.project.emissionsapi.entity.CityAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityAdminRepository extends JpaRepository<CityAdmin, Long> {

    Optional<CityAdmin> findOneByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

}
