/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
 * <p> Description: TODO </p>
 * <p> Created: Nov 9, 2020 </p>
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
	public FavouriteService(FavouriteRepository favouriteRepository, RoomRepository roomRepository, AnimalRepository animalRepository) {
		this.favouriteRepository = favouriteRepository;
		this.roomRepository = roomRepository;
		this.animalRepository = animalRepository;
	}

	public Favourite createFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.save(new Favourite(room, animal));
	}

	public void deleteFavourite(Long roomId, Long animalId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		List<Favourite> favourites = favouriteRepository.findAll(Example.of(new Favourite(room, animal)));
		if (CollectionUtils.isEmpty(favourites)) {
			favouriteRepository.delete(favourites.get(0));
		} else {
			throw new FavouriteNotFoundException(roomId, animalId);
		}
	}

	public List<String> getFavouriteRooms(Long animalId) {
		Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(animalId));

		return favouriteRepository.findAll(Example.of(new Favourite(animal))).stream().map(e -> e.getRoom().getTitle()).collect(Collectors.toList());
	}

}
