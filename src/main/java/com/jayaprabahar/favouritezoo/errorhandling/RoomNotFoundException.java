package com.jayaprabahar.favouritezoo.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomNotFoundException.java </p>
 * <p> Description: Exception should be thrown when Room Not found </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096280190287824998L;

	/**
	 * @param id Long Room Id
	 */
	public RoomNotFoundException(Long id) {
		super(String.format("Could not find room with id %s", id));
	}

}
