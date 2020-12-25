package com.jayaprabahar.favouritezoo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.service.AnimalRoomService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRoomController.java </p>
 * <p> Description: Controller class wrt Animal & Room combination </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
public class AnimalRoomController {

	private final AnimalRoomService animalRoomService;

	public AnimalRoomController(AnimalRoomService animalRoomService) {
		this.animalRoomService = animalRoomService;
	}

	/**
	 * Places the animal into the room
	 * 
	 * @param roomId Long
	 * @param animalId Long
	 * @return Animal - Updated animal object
	 */
	@PatchMapping("/rooms/{roomId}/animals/{animalId}")
	public Animal placeAnimalIntoRoom(@PathVariable final Long roomId, @PathVariable final Long animalId) {
		return animalRoomService.addRoomForAnimal(roomId, animalId);
	}

	/**
	 * Moves an animal from one room to another. If animal is not already present in the original room, then exception is thrown
	 * 
	 * @param roomId Long
	 * @param animalId Long
	 * @param newRoomId Long
	 * @return Animal - Updated animal object
	 */
	@PatchMapping("/rooms/{roomId}/animals/{animalId}/{newRoomId}")
	public Animal moveAnimalBetweenRooms(@PathVariable final Long roomId, @PathVariable final Long animalId,
			@PathVariable Long newRoomId) {
		return animalRoomService.updateNewRoomForAnimal(roomId, animalId, newRoomId);
	}

	/**
	 * Removes one animal from one room to another
	 * 
	 * @param animalId Long
	 * @return Animal - Updated animal object
	 */
	@PatchMapping("/rooms/animals/{animalId}")
	public Animal removeRoomForAnimal(@PathVariable final Long animalId) {
		return animalRoomService.removeRoomForAnimal(animalId);
	}

	/**
	 * List All the Animals In Room
	 * 
	 * @param roomId Long
	 * @param pageable Pageable
	 * @return List<AnimalResponseDto> - List of AnimalResponseDto
	 */
	@GetMapping("/rooms/{roomId}/animals")
	public List<AnimalResponseDto> listAllAnimalsInRoom(@PathVariable final Long roomId, Pageable pageable) {
		return animalRoomService.findAllAnimalsInRoom(roomId, pageable);
	}

	/**
	 * Returns the map of all rooms and the possible happy animal lists
	 * 
	 * @return Map<String, Long>
	 */
	@GetMapping("/happyAnimals")
	public Map<String, Long> listHappyAnimals() {
		return animalRoomService.getHappyAnimalsListPerRoom();
	}

}
