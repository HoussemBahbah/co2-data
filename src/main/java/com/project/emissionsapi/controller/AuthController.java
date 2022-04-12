package com.project.emissionsapi.controller;


import com.project.emissionsapi.config.JwtTokenUtil;
import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.model.LoginRequest;
import com.project.emissionsapi.model.LoginResponse;
import com.project.emissionsapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
			throws Exception {
		UserDetail userDetails;
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		userDetails = (UserDetail) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new LoginResponse(token));

	}


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
	}
}
