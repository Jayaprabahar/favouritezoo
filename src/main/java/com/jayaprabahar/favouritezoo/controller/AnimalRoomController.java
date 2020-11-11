/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
 * <p> Description: TODO </p>
 * <p> Created: Nov 9, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
public class AnimalRoomController {

	private AnimalRoomService animalRoomService;

	@Autowired
	public AnimalRoomController(AnimalRoomService animalRoomService) {
		this.animalRoomService = animalRoomService;
	}

	// Tested
	@PatchMapping("/rooms/{roomId}/animals/{animalId}")
	public Animal placeAnimalIntoRoom(@PathVariable final Long roomId, @PathVariable final Long animalId) {
		return animalRoomService.addRoomForAnimal(roomId, animalId);
	}

	// Tested
	@PatchMapping("/rooms/{roomId}/animals/{animalId}/{newRoomId}")
	public Animal moveAnimalBetweenRooms(@PathVariable final Long roomId, @PathVariable final Long animalId, @PathVariable Long newRoomId) {
		return animalRoomService.updateNewRoomForAnimal(roomId, animalId, newRoomId);
	}

	// Tested
	@PatchMapping("/rooms/animals/{animalId}")
	public Animal removeRoomForAnimal(@PathVariable final Long animalId) {
		return animalRoomService.removeRoomForAnimal(animalId);
	}

	// Tested
	@GetMapping("/rooms/{roomId}/animals")
	public List<AnimalResponseDto> listAllAnimalsInRoom(@PathVariable final Long roomId, Pageable pageable) {
		return animalRoomService.findAllAnimalsInRoom(roomId, pageable);
	}

	/**
	 * 
	 */
	@GetMapping("/happyAnimals")
	public Map<String, Long> listHappyAnimals() {
		return animalRoomService.listHappyAnimals();
	}

}
