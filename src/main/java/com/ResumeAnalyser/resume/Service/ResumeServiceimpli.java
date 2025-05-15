package com.ResumeAnalyser.resume.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ResumeAnalyser.resume.Model.Resume;
import com.ResumeAnalyser.resume.Model.User;
import com.ResumeAnalyser.resume.repository.ResumeResository;
import com.ResumeAnalyser.resume.repository.UserRepository;

@Service
public class ResumeServiceimpli implements ResumeService {

	@Autowired
	private ResumeResository resumeRepo;

	@Override
	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}

	@Override
	public Resume getResumebyId(Long id) {

		return resumeRepo.findById(id).orElse(null);
	}

	@Override
	public List<Resume> GetAllResume() {

		return resumeRepo.findAll();
	}

	@Override
	public List<Resume> GetAllResumebyUserId(Long userid) {
		// TODO Auto-generated method stub
		return resumeRepo.findByUserId(userid);
	}

	@Override
	public void deleteResumebyid(Long id) {
		resumeRepo.deleteById(id);

	}

//	public Resume saveParsedResume(String filename, String extractedText, Long userId) {
//	    return userRepo.findById(userId).map(user -> {
//	        Resume resume = Resume.builder()
//	                .filename(filename)
//	                .extractedText(extractedText)
//	                .upload_date(java.time.LocalDate.now())
//	                .user(user)
//	                .build();
//	        return resumeRepo.save(resume);
//	    }).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
//	}

}
