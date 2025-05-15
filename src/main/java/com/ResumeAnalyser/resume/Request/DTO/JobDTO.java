package com.ResumeAnalyser.resume.Request.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobDTO {

	private Long id;
	private String title;
	private String description;
	private List<String> requiredSkills;
	private List<String> requiredEducation;
	private List<String> requiredExperience;
	private String location;
	private String company;

}
