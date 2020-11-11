/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotInTheRoomException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;
import com.jayaprabahar.favouritezoo.repository.RoomRepository;
import com.jayaprabahar.favouritezoo.util.FavouriteZooScriptEngine;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRoomService.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Service
public class AnimalRoomService {

	private AnimalRepository animalRepository;
	private RoomRepository roomRepository;
	private FavouriteZooScriptEngine scriptEngine;

	/**
	 * 
	 */
	@Autowired
	public AnimalRoomService(AnimalRepository animalRepository, RoomRepository roomRepository, FavouriteZooScriptEngine scriptEngine) {
		this.animalRepository = animalRepository;
		this.roomRepository = roomRepository;
		this.scriptEngine = scriptEngine;
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
		Room newRoom = roomRepository.findById(newRoomId).orElseThrow(() -> new RoomNotFoundException(newRoomId));
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
	public List<AnimalResponseDto> findAllAnimalsInRoom(Long roomId, Pageable pageable) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));

		return animalRepository.findAllByRoom(room, pageable).toList().stream().map(e -> AnimalResponseDto.builder().title(e.getTitle()).located(e.getLocated()).build()).collect(Collectors.toList());
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

	/**
	 * @return
	 */
	public Map<String, Long> listHappyAnimals() {
		List<Animal> animals = animalRepository.findAll();
		Map<String, Long> roomHappyAnimalMap = new HashMap<>();

		roomRepository.findAll().forEach(eachRoom -> {
			roomHappyAnimalMap.put(eachRoom.getTitle(),
					animals.stream().filter(animal -> scriptEngine.evaluateBooleanScripts(eachRoom.getSize() + animal.getType() + String.valueOf(animal.getPreference()))).count());
		});
		return roomHappyAnimalMap;
	}

}
