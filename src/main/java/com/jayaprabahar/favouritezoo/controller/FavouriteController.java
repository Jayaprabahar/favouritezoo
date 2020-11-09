/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/favouritezoo/rooms")
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
	@PostMapping("/{roomId}/animals/{animalId}")
	public Favourite placeAnimalInRoom(@PathVariable(name = "roomId") Long roomId, @PathVariable(name = "animalId") Long animalId) {
		return favouriteService.createFavourite(roomId, animalId);
	}
	
	/**
	 * @return 
	 * 
	 */
	@DeleteMapping("/{roomId}/animals/{animalId}")
	public Favourite removeAnimalFromRoom(@PathVariable(name = "roomId") Long roomId, @PathVariable(name = "animalId") Long animalId) {
		return favouriteService.createFavourite(roomId, animalId);
	}


}
