package com.project.emissionsapi.repositories;


import com.project.emissionsapi.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long>{

	Optional<UserDetail> findOneByUsernameIgnoreCase(String username);
	boolean existsByUsernameIgnoreCase(String username);

}
