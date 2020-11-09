/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.errorhandling.FavouriteNotFoundException;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.repository.FavouriteRepository;

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

	/**
	 * 
	 */
	public FavouriteService(FavouriteRepository favouriteRepository) {
		this.favouriteRepository = favouriteRepository;
	}

	public Favourite createFavourite(Long roomId, Long animalId) {
		return favouriteRepository.save(new Favourite(roomId, animalId));
	}

	public void deleteFavourite(Long roomId, Long animalId) {
		favouriteRepository.findByRoomIdAndAnimalId(roomId, animalId)
			.ifPresentOrElse(e -> favouriteRepository.delete(e), 
					() -> { throw new FavouriteNotFoundException(roomId, animalId);
					});
	}
}
