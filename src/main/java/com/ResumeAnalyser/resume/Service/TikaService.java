package com.ResumeAnalyser.resume.Service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TikaService {

    private final Tika tika = new Tika();

    public String extractTextFromFile(org.springframework.web.multipart.MultipartFile file) throws Exception {
        return tika.parseToString(file.getInputStream());
    }

    public Map<String, List<String>> extractStructuredData(String text) {
        Map<String, List<String>> data = new HashMap<>();

        data.put("skills", extractListAfterHeader(text, "Technical Skills"));
        data.put("education", extractListAfterHeader(text, "Education"));
        data.put("experience", extractExperience(text));
        data.put("certifications", extractListAfterHeader(text, "Certifications"));

        return data;
    }

    public String extractSummary(String text) {
        return extractSingleSection(text, "Professional Summary").trim();
    }

    private List<String> extractListAfterHeader(String text, String header) {
        String section = extractSingleSection(text, header);
        if (section.isEmpty()) return List.of();

        // Split by new lines or bullet-like characters
        return Arrays.stream(section.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }

    private List<String> extractExperience(String text) {
        // If you want to treat Projects + Internship as "Experience"
        List<String> projects = extractListAfterHeader(text, "Projects");
        List<String> internship = extractListAfterHeader(text, "Internship");

        List<String> combined = new ArrayList<>();
        combined.addAll(projects);
        combined.addAll(internship);

        return combined;
    }

    private String extractSingleSection(String text, String sectionTitle) {
        Pattern pattern = Pattern.compile("(?i)###\\s*" + Pattern.quote(sectionTitle) + "\\s*\\n(.*?)(?=\\n###|\\Z)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }
}
