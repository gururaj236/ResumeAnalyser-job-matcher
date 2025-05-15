package com.ResumeAnalyser.resume.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	LocalDateTime timeStamp;
	int Status;
	String error;
	String message;

}
