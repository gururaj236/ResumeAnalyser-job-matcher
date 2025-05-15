package com.ResumeAnalyser.resume.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ResumeAnalyser.resume.Model.Resume;
import com.ResumeAnalyser.resume.Model.User;
import com.ResumeAnalyser.resume.Request.DTO.ResumeDTO;
import com.ResumeAnalyser.resume.Service.ResumeServiceimpli;
import com.ResumeAnalyser.resume.Service.TikaService;
import com.ResumeAnalyser.resume.Service.UserServiceimpli;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeServiceimpli resumeService;

    @Autowired
    private TikaService tikaService;

    @Autowired
    private UserServiceimpli userService;

    @PostMapping("/upload")
    public ResponseEntity<ResumeDTO> uploadResume(
            @RequestParam MultipartFile file,
            @RequestParam Long userId) {
        try {
         
            String extractedText = tikaService.extractTextFromFile(file);
            
           
            Map<String, List<String>> structuredData = tikaService.extractStructuredData(extractedText);
            
           
            String summary = tikaService.extractSummary(extractedText);
            
           
            if (extractedText.isBlank() && structuredData.values().stream().allMatch(List::isEmpty)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Reject empty resume
            }
            
          
            User user = userService.getuserbyId(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

         
            Resume resume = Resume.builder()
                    .filename(file.getOriginalFilename())
                    .extractedText(extractedText)
                    .uploadDate(java.time.LocalDate.now())
                    .skills(structuredData.getOrDefault("skills", List.of()))
                    .education(structuredData.getOrDefault("education", List.of()))
                    .experience(structuredData.getOrDefault("experience", List.of()))
                    .certifications(structuredData.getOrDefault("certifications", List.of()))
                    .summary(summary.isEmpty() ? "Not Provided" : summary)
                    .user(user)
                    .build();

           
            Resume savedResume = resumeService.saveResume(resume);
            return ResponseEntity.ok(mapToDTO(savedResume));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResumeDTO> getResumebyId(@PathVariable Long id) {
        Resume userResume = resumeService.getResumebyId(id);
        if (userResume == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(mapToDTO(userResume));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResumeDTO>> getAllResumes() {
        List<ResumeDTO> resumeDTOs = resumeService.GetAllResume().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        if (resumeDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resumeDTOs);
        }
        return ResponseEntity.ok(resumeDTOs);
    }

    @GetMapping("/getbyuserid/{id}")
    public ResponseEntity<List<ResumeDTO>> getAllByUserId(@PathVariable Long id) {
        List<ResumeDTO> resumeDTOs = resumeService.GetAllResumebyUserId(id).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        if (resumeDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resumeDTOs);
        }
        return ResponseEntity.ok(resumeDTOs);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            resumeService.deleteResumebyid(id);
            return ResponseEntity.ok("Resume deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting the resume");
        }
    }

    private ResumeDTO mapToDTO(Resume resume) {
        ResumeDTO dto = new ResumeDTO();
        dto.setId(resume.getId());
        dto.setFileName(resume.getFilename());
        dto.setExtractedText(resume.getExtractedText());
        dto.setUploaddate(resume.getUploadDate());
        dto.setUserid(resume.getUser().getId());
        dto.setSkills(resume.getSkills());
        dto.setEducation(resume.getEducation());
        dto.setExperience(resume.getExperience());
        dto.setCertifications(resume.getCertifications());
        dto.setSummary(resume.getSummary());
        return dto;
    }
}
