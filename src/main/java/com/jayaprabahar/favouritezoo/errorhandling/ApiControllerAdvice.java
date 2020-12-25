package com.jayaprabahar.favouritezoo.errorhandling;

import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : ApiControllerAdvice.java </p>
 * <p> Description: RestControllerAdvice class</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestControllerAdvice
public class ApiControllerAdvice {

	/**
	 * Handles all the validation violations and converts to easily readable errors with comma separated
	 * 
	 * @param e MethodArgumentNotValidException
	 * @return ResponseEntity<String>  String ResponseEntity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ResponseEntity<>(
				e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles all unique constraint violations
	 * 
	 * @param e ConstraintViolationException
	 * @return ResponseEntity<String> String ResponseEntity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		if(e.getCause().getMessage().contains("Unique index or primary key violation")) {
			return new ResponseEntity<>("Information already exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles all other exception
	 *
	 * @param e Exception
	 * @return ResponseEntity<String> String ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
