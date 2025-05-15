package com.ResumeAnalyser.resume.Request.DTO;

import lombok.Data;

@Data
public class UserAuthRequestDTO {
	
	private Long authId;
	private String userName;
	private String role;
	private String password;

}
