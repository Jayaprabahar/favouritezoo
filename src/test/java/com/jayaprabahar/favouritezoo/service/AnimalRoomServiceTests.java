/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotInTheRoomException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRoomServiceTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
class AnimalRoomServiceTests {

	@Autowired
	private AnimalRoomService animalRoomService;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private RoomService roomService;

	/**
	 * 
	 */
	@Test
	void testAddRoomForAnimal() {
		Animal animal = animalService.createAnimal(new AnimalDto("New Cow", ">=", 20l));
		Room room = roomService.createRoom(new RoomDto("Yellow", 50l));

		assertThrows(RoomNotFoundException.class, () -> {
			animalRoomService.addRoomForAnimal(room.getId() + 10, animal.getId());
		});
		assertThrows(AnimalNotFoundException.class, () -> {
			animalRoomService.addRoomForAnimal(room.getId(), animal.getId() + 10);
		});

		assertNull(animal.getRoom());

		Animal animal2 = animalRoomService.addRoomForAnimal(room.getId(), animal.getId());
		assertNotNull(animal2.getRoom());
		assertEquals(room.getTitle(), animal2.getRoom().getTitle());
		assertEquals(room.getSize(), animal2.getRoom().getSize());
	}

	@Test
	void testUpdateRoomForAnimal() {
		Animal animal = animalService.createAnimal(new AnimalDto("New Cow2", ">=", 20l));
		Room room = roomService.createRoom(new RoomDto("Yellow2", 50l));
		Room room2 = roomService.createRoom(new RoomDto("Blue2", 35l));

		assertThrows(AnimalNotInTheRoomException.class, () -> {
			animalRoomService.updateNewRoomForAnimal(room.getId(), animal.getId(), room2.getId());
		});
		animalRoomService.addRoomForAnimal(room.getId(), animal.getId());

		Animal animal2 = animalRoomService.updateNewRoomForAnimal(room.getId(), animal.getId(), room2.getId());

		assertNotNull(animal2.getRoom());
		assertEquals(room2.getTitle(), animal2.getRoom().getTitle());
	}

	@Test
	void testRemoveRoomForAnimal() {
		Animal animal = animalService.createAnimal(new AnimalDto("New Cow", ">=", 20l));
		Room room = roomService.createRoom(new RoomDto("Blue", 35l));

		assertThrows(AnimalNotFoundException.class, () -> {
			animalRoomService.removeRoomForAnimal(animal.getId() + 1);
		});

		Animal animal2 = animalRoomService.addRoomForAnimal(room.getId(), animal.getId());
		assertNotNull(animal2.getRoom());

		animal2 = animalRoomService.removeRoomForAnimal(animal.getId());
		assertNull(animal2.getRoom());
	}

	@Test
	void testGetHappyAnimalsListPerRoom() {
		animalService.createAnimal(new AnimalDto("Great Dane", ">=", 100l));
		animalService.createAnimal(new AnimalDto("Boerboel", ">=", 80l));
		animalService.createAnimal(new AnimalDto("Leonberger", ">=", 60l));
		animalService.createAnimal(new AnimalDto("Great Pyrenees", ">=", 40l));
		Animal animal5 = animalService.createAnimal(new AnimalDto("Tosa", ">=", 20l));

		roomService.createRoom(new RoomDto("Big Big Green Room", 150l));
		roomService.createRoom(new RoomDto("Big Blue Room", 90l));
		roomService.createRoom(new RoomDto("Medium violet Room", 70l));
		roomService.createRoom(new RoomDto("Small pink Room", 50l));
		Room room5 = roomService.createRoom(new RoomDto("Smallest purple Room", 30l));

		Map<String, Long> happyAnimalMap = Map.of("Big Big Green Room", 5l, "Big Blue Room", 4l, "Medium violet Room", 3l, "Small pink Room", 2l, "Smallest purple Room", 1l);
		assertEquals(happyAnimalMap, animalRoomService.getHappyAnimalsListPerRoom());

		roomService.deleteRoom(room5.getId());

		happyAnimalMap = Map.of("Big Big Green Room", 5l, "Big Blue Room", 4l, "Medium violet Room", 3l, "Small pink Room", 2l);
		assertEquals(happyAnimalMap, animalRoomService.getHappyAnimalsListPerRoom());

		animalService.deleteAnimal(animal5.getId());

		happyAnimalMap = Map.of("Big Big Green Room", 4l, "Big Blue Room", 3l, "Medium violet Room", 2l, "Small pink Room", 1l);
		assertEquals(happyAnimalMap, animalRoomService.getHappyAnimalsListPerRoom());
	}

}
