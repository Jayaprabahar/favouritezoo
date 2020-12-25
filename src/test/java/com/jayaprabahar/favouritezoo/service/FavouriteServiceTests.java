package com.jayaprabahar.favouritezoo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.FavouriteNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteServiceTests.java </p>
 * <p> Description: Tests FavouriteService class </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
class FavouriteServiceTests {

	@Autowired
	private FavouriteService favouriteService;
	@Autowired
	private AnimalService animalService;
	@Autowired
	private RoomService roomService;

	@Test
	void testCreateFavourite() {
		Animal animal = animalService.createAnimal(new AnimalDto("Puppy", ">=", 34L));
		Room room = roomService.createRoom(new RoomDto("Yellow", 50L));
		Favourite favourite = favouriteService.createFavourite(room.getId(), animal.getId());

		assertNotNull(favourite);
		assertTrue(favourite.getId() >= 1);
		assertEquals(50L, favourite.getRoom().getSize());
		assertEquals(34L, favourite.getAnimal().getPreference());
	}

	@Test
	void testAllNotFoundExceptions() {
		Animal animal = animalService.createAnimal(new AnimalDto("Puppy", ">=", 34L));
		Room room = roomService.createRoom(new RoomDto("Yellow", 50L));
		Room room2 = roomService.createRoom(new RoomDto("Blue", 5L));

		assertThrows(RoomNotFoundException.class, () -> {
			favouriteService.createFavourite(room.getId() + 10, animal.getId());
		});
		assertThrows(AnimalNotFoundException.class, () -> {
			favouriteService.createFavourite(room.getId(), animal.getId() + 10);
		});

		assertThrows(FavouriteNotFoundException.class, () -> {
			favouriteService.findFavourite(room.getId(), animal.getId());
		});

		assertNotNull(favouriteService.createFavourite(room.getId(), animal.getId()));
		assertNotNull(favouriteService.findFavourite(room.getId(), animal.getId()));

		assertThrows(FavouriteNotFoundException.class, () -> {
			favouriteService.deleteFavourite(room2.getId(), animal.getId());
		});

	}

	/**
	 * 
	 */
	@Test
	void testGetFavouriteRoomsByAnimalId() {
		Animal animal = animalService.createAnimal(new AnimalDto("Puppy", ">=", 34L));
		Room room = roomService.createRoom(new RoomDto("Yellow", 50L));
		Room room2 = roomService.createRoom(new RoomDto("Green", 40L));

		favouriteService.createFavourite(room.getId(), animal.getId());
		favouriteService.createFavourite(room2.getId(), animal.getId());

		List<String> favRooms = favouriteService.getFavouriteRoomsByAnimalId(animal.getId());

		assertTrue(CollectionUtils.containsAll(favRooms, List.of("Yellow", "Green")));
	}
}
