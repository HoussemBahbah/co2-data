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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;

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

			City Barcelona=addCity("Barcelona");
			District gracia=addDistrictToCity(new District("Gracia"),Barcelona);
			District eixample=addDistrictToCity(new District("Eixample"),Barcelona);


			City Wien=addCity("Wien");
			addDistrictToCity(new District("Währing"),Wien);
			addDistrictToCity(new District("Penzing"),Wien);

			cityRepository.save(Barcelona);
			cityRepository.save(Wien);

			addUser(username, userDetail, Barcelona);
		}



		@Bean
		CommandLineRunner initializeData(@Autowired CityRepository cityRepository) {
			return args -> {
				// save a couple of customers

			};
		}




		private District addDistrictToCity(District district, City city) {
			boolean isDistrictExist=districtRepository.existsByDistrictName(district.getDistrictName());
			if(!isDistrictExist){
				district.setCity(city);
				city.getDistricts().add(district);
				districtRepository.save(district);
				cityRepository.save(city);
			}

			return districtRepository.findByDistrictName(district.getDistrictName());
		}

		private void addUser(String username, UserDetail userDetail, City city) {
			boolean userExist = userDetailRepository.existsByUsername(username);
			if (!userExist) {
				userDetail.setUsername(username);
				userDetail.setPassword(passwordEncoder.encode("password"));
				userDetail.setCity(city);
				userDetailRepository.save(userDetail);
			}
		}


		public City addCity(String name){
			City city=new City(name);
			boolean isCityExist=cityRepository.existsByName(city.getName());
			if(!isCityExist){
				cityRepository.save(city);
			}
			return cityRepository.findByName(name);
		}

	}

}
