package com.project.emissionsapi.service;


import com.project.emissionsapi.entity.CityAdmin;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.CityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityAdminService implements UserDetailsService {

    @Autowired
    private CityAdminRepository cityAdminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CityAdmin cityAdmin = cityAdminRepository.findOneByUsernameIgnoreCase(username).orElse(null);
        return cityAdmin;
    }

    public MessageResponse save(CityAdmin user) {
        boolean exist = cityAdminRepository.existsById((user.getId()));
        if (exist) {
            return new MessageResponse(false, "Not Success", "Existing");
        }
        cityAdminRepository.save(user);
        return new MessageResponse(true, "Success", "Backend responded save ok");

    }

    public MessageResponse update(CityAdmin cityAdmin) {
        cityAdminRepository.save(cityAdmin);
        return new MessageResponse(true, "Success", "Backend responded update  ok");
    }

    public MessageResponse delete(Long id) {
        cityAdminRepository.deleteById(id);
        return new MessageResponse(true, "Success", "Backend responded delete ok");

    }

    public CityAdmin findById(Long id) {
        return cityAdminRepository.findById(id).orElse(null);
    }

    public List<CityAdmin> findAll() {
        return cityAdminRepository.findAll();
    }


}

