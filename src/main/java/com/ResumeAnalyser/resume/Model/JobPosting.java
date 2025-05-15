package com.ResumeAnalyser.resume.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@ElementCollection
	private List<String> requiredSkills;

	@ElementCollection
	private List<String> requiredEducation;

	@ElementCollection
	private List<String> requiredExperience;
	
	private String location;

	private String company;

}
