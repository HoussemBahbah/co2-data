package com.project.emissionsapi;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.repositories.CityRepository;
import com.project.emissionsapi.repositories.UserDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication

public class EmissionsApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmissionsApiApplication.class, args);

	}

	@Component
	@AllArgsConstructor
	public class StartRunner implements ApplicationRunner {

		@Autowired
		private UserDetailRepository userDetailRepository;
		@Autowired
		private CityRepository cityRepository;
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public void run(ApplicationArguments args) throws Exception {


			String username="user";
			UserDetail userDetail =new UserDetail();
			City Barcelona=addCity("Barcelona");
			Barcelona.setDistricts(Arrays.asList(
					new District("Gracia"),
					new District("Eixample")));
			Barcelona.getDistricts().get(0).setCo2Levels(Arrays.asList(new Co2Level("20","06/06/2022")));
			City Wien=addCity("Wien");
			Wien.setDistricts(Arrays.asList(new District("Währing"),new District("Penzing")));

			boolean userExist = userDetailRepository.existsByUsername(username);
			if (!userExist) {
				userDetail.setUsername(username);
				userDetail.setPassword(passwordEncoder.encode("password"));
				userDetail.setCity(Barcelona);
				userDetailRepository.save(userDetail);
			}
		}


		public City addCity(String name){
			City city=new City(name);
			boolean isCityExist=cityRepository.existsByName(city.getName());
			if(!isCityExist){
				cityRepository.save(city);
			}
			return city;
		}

	}

}
