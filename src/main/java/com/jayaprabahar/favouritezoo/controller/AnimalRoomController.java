/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/rooms/{roomId}/animals")
	public List<Animal> listAllAnimalsInRoom(@PathVariable final Long roomId, Pageable pageable) {
		return animalRoomService.findAllAnimalsInRoom(roomId, pageable);
	}

	@PutMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal placeAnimalIntoRoom(@PathVariable final Long roomId, @PathVariable final Long animalId) {
		return animalRoomService.addRoomForAnimal(roomId, animalId);
	}

	@PutMapping("/rooms/{roomId}/animals/{animalId}/{newRoomId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal moveAnimalBetweenRooms(@PathVariable final Long roomId, @PathVariable final Long animalId, @PathVariable Long newRoomId) {
		return animalRoomService.updateNewRoomForAnimal(roomId, animalId, newRoomId);
	}

	@PutMapping("/rooms//{animalId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal removeRoomForAnimal(@PathVariable final Long animalId) {
		return animalRoomService.removeRoomForAnimal(animalId);
	}

	/**
	 * 
	 */
	@GetMapping("/happyAnimals")
	public void listHappyAnimals() {

	}

}
