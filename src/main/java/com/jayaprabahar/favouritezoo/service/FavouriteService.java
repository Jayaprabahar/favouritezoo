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

	private final FavouriteRepository favouriteRepository;
	private final RoomRepository roomRepository;
	private final AnimalRepository animalRepository;

	/**
	 * @param favouriteRepository FavouriteRepository
	 * @param roomRepository RoomRepository
	 * @param animalRepository AnimalRepository
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
	 * @param roomId Long Room Id
	 * @param animalId Long Animal Id
	 * @return Favourite entity
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
	 * @param roomId Room Id
	 * @param animalId Animal Id
	 * @return Favourite Favourite entity
	 */
	public Favourite createFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.save(new Favourite(room, animal));
	}

	/**
	 * Deletes favourite combination, if present. If not throws exception
	 *  
	 * @param roomId Room id
	 * @param animalId Animal id
	 */
	public void deleteFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		favouriteRepository.findByRoomAndAnimal(room, animal).ifPresentOrElse(favouriteRepository::delete,
				() -> { throw new FavouriteNotFoundException(roomId, animalId); });
	}

	/**
	 * Return Favourite rooms By AnimalId
	 * 
	 * @param animalId Animal Id
	 * @return List<String> List of fav room names
	 */
	public List<String> getFavouriteRoomsByAnimalId(Long animalId) {
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.findByAnimal(animal).stream().map(e -> e.getRoom().getTitle())
				.collect(Collectors.toList());
	}

}
