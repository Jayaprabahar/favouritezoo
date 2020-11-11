/**
 * 
 */
package com.jayaprabahar.favouritezoo.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalNotFoundException.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FavouriteNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096280190287824998L;

	/**
	 * 
	 */
	public FavouriteNotFoundException(Long roomId, Long animalId) {
		super(String.format("Could not find room, animal mapping with roomId %d and animalId %d", roomId, animalId));
	}

}
