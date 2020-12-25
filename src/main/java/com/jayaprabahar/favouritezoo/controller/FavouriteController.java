package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * <p> Description: Controller class wrt Favourite table</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
@RequestMapping("/favourite")
public class FavouriteController {

	private final FavouriteService favouriteService;

	/**
	 * 
	 */
	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	/**
	 * Returns the Assigned favourite Room For the Animal.
	 * 
	 * @param roomId Long
	 * @param animalId Long
	 * @return Favourite - assigned Favourite combination
	 */
	@GetMapping("/rooms/{roomId}/animals/{animalId}")
	public Favourite findAssignedFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId,
			@PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.findFavourite(roomId, animalId);
	}

	/**
	 * Assigns a favourite room for animal
	 * 
	 * @param roomId Long
	 * @param animalId Long
	 * @return Favourite - assigned Favourite combination
	 */
	@PostMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Favourite assignFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId,
			@PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.createFavourite(roomId, animalId);
	}

	/**
	 * Unassign the favourite room for animal
	 * 
	 * @param roomId long Room Id
	 * @param animalId long animal Id
	 * @return ResponseEntity<GenericResponseDto>
	 */
	@DeleteMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<String> unAssignFavouriteRoomForAnimal(
			@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		favouriteService.deleteFavourite(roomId, animalId);
		return new ResponseEntity<>(String.format("Favourite room is unassigned for room %d and animal %d", roomId, animalId),
				HttpStatus.NO_CONTENT);
	}

	/**
	 * Lists all FavoriteRooms
	 * 
	 * @param animalId Long Animal Id
	 * @return List<String>
	 */
	@GetMapping("/animals/{animalId}")
	public List<String> listOfFavoriteRooms(@PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.getFavouriteRoomsByAnimalId(animalId);
	}

}
