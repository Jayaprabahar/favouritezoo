/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotInTheRoomException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;
import com.jayaprabahar.favouritezoo.repository.RoomRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRoomService.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 9, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
public class AnimalRoomService {

	private AnimalRepository animalRepository;
	private RoomRepository roomRepository;

	/**
	 * 
	 */
	public AnimalRoomService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	/**
	 * @param roomId
	 * @param animalId
	 * @return 
	 */
	public Animal addRoomForAnimal(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));
		animal.setRoom(room);
		return animalRepository.save(animal);
	}

	/**
	 * @param roomId
	 * @param animalId
	 * @param newRoomId
	 * @return 
	 */
	public Animal updateNewRoomForAnimal(Long roomId, Long animalId, Long newRoomId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Room newRoom = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(newRoomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));
		
		if (animal.getRoom().getId() == room.getId()) {
			animal.setRoom(newRoom);
			return animalRepository.save(animal);
		} else {
			throw new AnimalNotInTheRoomException(animalId, roomId);
		}

	}

	/**
	 * @param roomId
	 * @param sortingOrder 
	 * @param sortingVaraible 
	 * @return
	 */
	public List<Animal> findAllAnimalsInRoom(Long roomId, Pageable pageable) {
		return animalRepository.findAllById(roomId, pageable).toList();
	}

	/**
	 * @param animalId
	 * @return 
	 */
	public Animal removeRoomForAnimal(Long animalId) {
		return animalRepository.findById(animalId).map(animal -> {
			animal.setRoom(null);
			return animalRepository.save(animal);
		}).orElseThrow(() -> new AnimalNotFoundException(animalId));
	}

}
