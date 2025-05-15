package com.ResumeAnalyser.resume.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ResumeAnalyser.resume.Model.UserAuthEntity;
import com.ResumeAnalyser.resume.Request.DTO.UserAuthRequestDTO;
import com.ResumeAnalyser.resume.Service.UserAuthService;
import com.ResumeAnalyser.resume.response.DTO.UserAuthResponseDTO;

@RestController
@RequestMapping("/Auth/")
public class Authcontroller {

	@Autowired
	private UserAuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/create")
	public UserAuthResponseDTO createUser(@RequestBody UserAuthRequestDTO requestDTO) {
		UserAuthEntity entity = new UserAuthEntity();
		entity.setUserName(requestDTO.getUserName());
		entity.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
		entity.setRole(requestDTO.getRole());

		UserAuthEntity savedUser = authService.CreateUser(entity);

		return convertToResponse(savedUser);
	}

	@GetMapping("/get/{id}")
	public UserAuthResponseDTO getUserById(@PathVariable Long id) {
		UserAuthEntity user = authService.getUserByUserId(id);

		return convertToResponse(user);
	}

	@GetMapping("/getAll")
	public List<UserAuthResponseDTO> getallUser() {
		List<UserAuthEntity> user = authService.getAllUser();
		return user.stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	@PutMapping("/update/{id}")
	public UserAuthResponseDTO update(@PathVariable Long id, @RequestBody UserAuthRequestDTO dto) {
		UserAuthEntity user = new UserAuthEntity();
		String encodedPassword=passwordEncoder.encode(dto.getPassword());
		user.setUserName(dto.getUserName());
		user.setPassword(encodedPassword);
		user.setRole(dto.getRole());

		UserAuthEntity updatedUser = authService.Updateuser(id, user);

		return convertToResponse(updatedUser);

	}
	
	

	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id) {
		authService.DeletebyUser(id);
		return ("user Deleted successfully with id :" + id);
	}

	private UserAuthResponseDTO convertToResponse(UserAuthEntity auth) {
		UserAuthResponseDTO dto = new UserAuthResponseDTO();
		dto.setAuthId(auth.getAuthId());
		dto.setUserName(auth.getUserName());
		dto.setRole(auth.getRole());
		return dto;
	}

}
