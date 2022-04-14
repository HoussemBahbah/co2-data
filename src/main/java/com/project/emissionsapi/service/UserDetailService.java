package com.project.emissionsapi.service;


import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail userDetail = userDetailRepository.findOneByUsernameIgnoreCase(username).orElse(null);
		return userDetail;
	}

	public MessageResponse save(UserDetail user){
		boolean exist = userDetailRepository.existsById((user.getId()));
		if (exist) {
			return new MessageResponse(false,"Not Success","Existing");
		}
		userDetailRepository.save(user);
		return new MessageResponse(true, "Success", "Backend responded save ok");

	}

	public MessageResponse update(UserDetail userDetail){
		userDetailRepository.save(userDetail);
		return new MessageResponse(true, "Success", "Backend responded update  ok");
	}

	public MessageResponse delete(Long id) {
		userDetailRepository.deleteById(id);
		return new MessageResponse(true, "Success", "Backend responded delete ok");

	}

	public UserDetail findById(Long id) {
		return userDetailRepository.findById(id).orElse(null);
	}

	public List<UserDetail> findAll() {
		return userDetailRepository.findAll();
	}



}

