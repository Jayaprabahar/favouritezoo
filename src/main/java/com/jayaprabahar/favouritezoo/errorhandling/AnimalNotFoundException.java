package com.jayaprabahar.favouritezoo.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalNotFoundException.java </p>
 * <p> Description: Exception should be thrown when Animal Not Found</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9017637171460963897L;

	/**
	 * @param id long id passed for finding Animal entity
	 */
	public AnimalNotFoundException(Long id) {
		super(String.format("Could not find animal with id %s", id));
	}

}
