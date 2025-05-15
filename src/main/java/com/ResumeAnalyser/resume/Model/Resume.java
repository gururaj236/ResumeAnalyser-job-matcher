package com.ResumeAnalyser.resume.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String filename;

	@Lob
	private String extractedText;

	private LocalDate uploadDate;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> education;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "resume_experience", joinColumns = @JoinColumn(name = "resume_id"))
	@Column(name = "experience_entry")
	private List<String> experience;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> skills;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> certifications;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String summary;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
