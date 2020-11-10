/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.service.FavouriteService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteController.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 9, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
@RequestMapping("/favourite")
public class FavouriteController {

	private FavouriteService favouriteService;

	/**
	 * 
	 */
	@Autowired
	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	/**
	 * @return 
	 * 
	 */
	@PostMapping("/rooms/{roomId}/animals/{animalId}/")
	@ResponseStatus(HttpStatus.CREATED)
	public Favourite assignFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.createFavourite(roomId, animalId);
	}

	/**
	 * @return 
	 * 
	 */
	@DeleteMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void unAssignFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		favouriteService.deleteFavourite(roomId, animalId);
	}

	/**
	 * 
	 */
	@GetMapping("/animals/{animalId}")
	public List<String> listOfFavoriteRooms(@PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.getFavouriteRooms(animalId);
	}

}
