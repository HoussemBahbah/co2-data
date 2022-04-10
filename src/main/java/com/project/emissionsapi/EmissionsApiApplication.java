package com.project.emissionsapi;

import com.project.emissionsapi.entity.City;
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
			City Barcelona=addCity("Barcelona","20","district6");
			City Wien=addCity("Wien","30","district6");

			boolean userExist = userDetailRepository.existsByUsername(username);
			if (!userExist) {
				userDetail.setUsername(username);
				userDetail.setPassword(passwordEncoder.encode("password"));
				userDetail.setCity(Barcelona);
				userDetailRepository.save(userDetail);
			}
		}


		public City addCity(String name,String co2Level,String district){
			City city=new City(name,co2Level,district);
			boolean isCityExist=cityRepository.existsByName(city.getName());
			if(!isCityExist){
				cityRepository.save(city);
			}
			return city;
		}

	}

}
