package com.ResumeAnalyser.resume.response.DTO;

import lombok.Data;

@Data
public class UserAuthResponseDTO {
	
	private Long authId;
	private String userName;
	private String role;

}
