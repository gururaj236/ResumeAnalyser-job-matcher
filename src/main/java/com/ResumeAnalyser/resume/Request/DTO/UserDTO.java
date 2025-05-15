package com.ResumeAnalyser.resume.Request.DTO;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	private String name;
	private String Email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

}
