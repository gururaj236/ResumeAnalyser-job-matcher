package com.ResumeAnalyser.resume.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ResumeAnalyser.resume.Model.User;
import com.ResumeAnalyser.resume.Request.DTO.UserDTO;
import com.ResumeAnalyser.resume.Service.UserService;
import com.ResumeAnalyser.resume.Service.UserServiceimpli;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServiceimpli userService;
	
	@PostMapping("/create")
	public UserDTO createUser( @RequestBody User user) {
		User savedUser= userService.saveUser(user);
		return mapToDTO(savedUser);
	}
	
	@GetMapping("/get/{id}")
	public UserDTO getUser(@PathVariable Long id) {
		return mapToDTO(userService.getuserbyId(id));
		
	}
	@GetMapping("/all")
	public List<UserDTO>getallUser(){
		return userService.getAllUser()
				.stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userService.deletebyId(id);
		
	}
	
	@PutMapping("/update/{id}")
	public User update(@PathVariable Long id,@RequestBody User user) {
		return userService.Update(id,user);
	}
	
	
	private UserDTO mapToDTO(@RequestBody User user) {
		
		UserDTO dto=new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		
		return dto;
		
		
	}
	
	

}
