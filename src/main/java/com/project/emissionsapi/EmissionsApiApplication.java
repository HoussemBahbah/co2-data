package com.project.emissionsapi;

import com.project.emissionsapi.entity.City;
import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.repositories.CityRepository;
import com.project.emissionsapi.repositories.Co2LevelRepository;
import com.project.emissionsapi.repositories.DistrictRepository;
import com.project.emissionsapi.repositories.CityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication

public class EmissionsApiApplication {

    @Autowired
    private CityAdminRepository cityAdminRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private Co2LevelRepository co2LevelRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EmissionsApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeData() {
        return args -> {
            //Barcelona City creation
            City Barcelona = addCity("Barcelona");
            District gracia = addDistrictToCity(new District("Gràcia"), Barcelona);
            District eixample = addDistrictToCity(new District("Eixample"), Barcelona);
            //Wien City creation
            City Wien = addCity("Wien");
            addDistrictToCity(new District("Währing"), Wien);
            addDistrictToCity(new District("Penzing"), Wien);
            //München City creation
            City München = addCity("München");
            addDistrictToCity(new District("Maxvorstadt"), München);
            //Save
            cityRepository.save(Barcelona);
            cityRepository.save(Wien);
            cityRepository.save(München);
            //City Admin creation
            CityAdmin barcelonaAdmin = new CityAdmin();
            CityAdmin wienAdmin = new CityAdmin();
            addUser("barcelonaAdmin", barcelonaAdmin, Barcelona);
            addUser("wienAdmin", wienAdmin, Wien);
        };
    }


    private District addDistrictToCity(District district, City city) {
        boolean isDistrictExist = districtRepository.existsByDistrictNameIgnoreCase(district.getDistrictName());
        if (!isDistrictExist) {
            district.setCity(city);
            city.getDistricts().add(district);
            districtRepository.save(district);
            cityRepository.save(city);
        }
        return districtRepository.findByDistrictNameIgnoreCase(district.getDistrictName());
    }

    private void addUser(String username, CityAdmin cityAdmin, City city) {
        boolean userExist = cityAdminRepository.existsByUsernameIgnoreCase(username);
        if (!userExist) {
            cityAdmin.setUsername(username);
            cityAdmin.setPassword(passwordEncoder.encode("password"));
            cityAdmin.setCity(city);
            cityAdminRepository.save(cityAdmin);
        }
    }

    public City addCity(String name) {
        City city = new City(name);
        boolean isCityExist = cityRepository.existsByNameIgnoreCase(city.getName());
        if (!isCityExist) {
            cityRepository.save(city);
        }
        return cityRepository.findByNameIgnoreCase(name);
    }

}
