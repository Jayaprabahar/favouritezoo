/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.FavouriteNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;
import com.jayaprabahar.favouritezoo.repository.FavouriteRepository;
import com.jayaprabahar.favouritezoo.repository.RoomRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteService.java </p>
 * <p> Description: Service layer for Favourites entities</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Service
public class FavouriteService {

	private FavouriteRepository favouriteRepository;
	private RoomRepository roomRepository;
	private AnimalRepository animalRepository;

	/**
	 * 
	 */
	/**
	 * @param favouriteRepository
	 * @param roomRepository
	 * @param animalRepository
	 */
	public FavouriteService(FavouriteRepository favouriteRepository, RoomRepository roomRepository,
			AnimalRepository animalRepository) {
		this.favouriteRepository = favouriteRepository;
		this.roomRepository = roomRepository;
		this.animalRepository = animalRepository;
	}

	/**
	 * Find if any favourite combination is made or not
	 * 
	 * @param roomId
	 * @param animalId
	 * @return Favourite
	 */
	public Favourite findFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.findByRoomAndAnimal(room, animal)
				.orElseThrow(() -> new FavouriteNotFoundException(roomId, animalId));
	}

	/**
	 * creates favourite combination
	 * 
	 * @param roomId
	 * @param animalId
	 * @return Favourite
	 */
	public Favourite createFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.save(new Favourite(room, animal));
	}

	/**
	 * Deletes favourite combination, if present. If not throws exception
	 *  
	 * @param roomId
	 * @param animalId
	 */
	public void deleteFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		favouriteRepository.findByRoomAndAnimal(room, animal).ifPresentOrElse(e -> favouriteRepository.delete(e),
				() -> {
					throw new FavouriteNotFoundException(roomId, animalId);
				});
	}

	/**
	 * Return Favourite ooms By AnimalId
	 * 
	 * @param animalId
	 * @return
	 */
	public List<String> getFavouriteRoomsByAnimalId(Long animalId) {
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.findByAnimal(animal).stream().map(e -> e.getRoom().getTitle())
				.collect(Collectors.toList());
	}

}
