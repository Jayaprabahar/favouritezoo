package com.jayaprabahar.favouritezoo.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalNotInTheRoomException.java </p>
 * <p> Description: Exception should be thrown when Animal Not in the room</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotInTheRoomException extends RuntimeException {

	private static final long serialVersionUID = -5096280190287824998L;

	/**
	 * @param animalId Long Animal Id
	 * @param roomId Long Room Id
	 */
	public AnimalNotInTheRoomException(Long animalId, Long roomId) {
		super(String.format("Animal %s is not inside the room %s", animalId, roomId));
	}

}
