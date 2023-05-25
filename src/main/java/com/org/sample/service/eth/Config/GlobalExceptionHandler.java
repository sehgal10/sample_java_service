package com.org.sample.service.eth.Config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.sample.service.eth.DTO.GeneralResponseDTO;


/**
 * Config class to handle an illegal servlet request.
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleIllegalArgumentException(Exception e, WebRequest request) {
		e.printStackTrace();
		return new ResponseEntity<Object>(new GeneralResponseDTO(e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		e.printStackTrace();
		return new ResponseEntity<Object>(new GeneralResponseDTO(e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		e.printStackTrace();
		return new ResponseEntity<Object>(new GeneralResponseDTO(e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}