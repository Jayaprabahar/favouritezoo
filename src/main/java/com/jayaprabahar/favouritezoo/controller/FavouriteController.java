/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import com.jayaprabahar.favouritezoo.dto.GenericResponseDto;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteController.java </p>
 * <p> Description: TODO </p>
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

	private FavouriteService favouriteService;

	/**
	 * 
	 */
	@Autowired
	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@GetMapping("/rooms/{roomId}/animals/{animalId}")
	public Favourite findFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.findFavourite(roomId, animalId);
	}

	/**
	 * @return 
	 * 
	 */
	@PostMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Favourite assignFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.createFavourite(roomId, animalId);
	}

	/**
	 * @return 
	 * @return 
	 * 
	 */
	@DeleteMapping("/rooms/{roomId}/animals/{animalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<GenericResponseDto> unAssignFavouriteRoomForAnimal(@PathVariable(name = "roomId") final Long roomId, @PathVariable(name = "animalId") final Long animalId) {
		favouriteService.deleteFavourite(roomId, animalId);
		return new ResponseEntity<>(GenericResponseDto.builder().message(String.format("Favourite room is unassigned for room %d and animal %d", roomId, animalId)).build(), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@GetMapping("/animals/{animalId}")
	public List<String> listOfFavoriteRooms(@PathVariable(name = "animalId") final Long animalId) {
		return favouriteService.getFavouriteRooms(animalId);
	}

}
