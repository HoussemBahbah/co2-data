package com.project.emissionsapi;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.Co2Level;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.repositories.CityRepository;
import com.project.emissionsapi.repositories.Co2LevelRepository;
import com.project.emissionsapi.repositories.DistrictRepository;
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
		private DistrictRepository districtRepository;
		@Autowired
		private Co2LevelRepository co2LevelRepository;
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public void run(ApplicationArguments args) throws Exception {


			String username="user";
			UserDetail userDetail =new UserDetail();

			City Barcelona=new City("Barcelona");
			District garcia=new District("Gracia");
			District eixample=new District("Eixample");
			Co2Level co2Level=new Co2Level("22","06/08/2022");
			co2Level.setDistrict(garcia);

			garcia.setCo2Levels(Arrays.asList(co2Level));
			garcia.setCity(Barcelona);
			eixample.setCity(Barcelona);
			Barcelona.setDistricts(Arrays.asList(garcia,eixample));
			cityRepository.save(Barcelona);
			districtRepository.save(garcia);
			districtRepository.save(eixample);
			co2LevelRepository.save(co2Level);
			//co2LevelRepository.save(co2Level);



			Barcelona.setDistricts(Arrays.asList(garcia,eixample));
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
