package com.org.sample.service.eth.Utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Utility class to help with api errors
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiError extends RuntimeException {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private Object debugMessage;

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus status) {
		this();
		this.setStatus(status);
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.setStatus(status);
		this.setMessage("Unexpected error");
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.setStatus(status);
		this.setMessage(message);
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	public ApiError(HttpStatus status, String message) {
		this();
		this.setStatus(status);
		this.setMessage(message);
	}

	public ApiError(HttpStatus status, String message, Object debugMessage) {
		this();
		this.setStatus(status);
		this.setMessage(message);
		this.setDebugMessage(debugMessage);
	}

}