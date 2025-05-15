package com.ResumeAnalyser.resume.Request.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ResumeDTO {

	private long id;
	private String fileName;
	private String extractedText;
	private LocalDate Uploaddate;
	private List<String> skills;
	private List<String> education;
	private List<String> experience;
	private List<String> certifications;
	private String summary;


	private Long userid;

	
}
