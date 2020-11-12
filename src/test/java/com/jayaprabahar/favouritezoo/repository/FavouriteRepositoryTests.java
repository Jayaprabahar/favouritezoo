/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteRepositoryTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class FavouriteRepositoryTests {

	@Autowired
	private FavouriteRepository favouriteRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Test
	@Order(1)
	void injectedComponentsAreNotNull() {
		assertThat(favouriteRepository).isNotNull();
	}

	@Test
	@Order(2)
	void whenNoEntries_thenResultIsEmpty() {
		assertTrue(favouriteRepository.findAll().isEmpty());
		assertTrue(favouriteRepository.findById(1l).isEmpty());
	}

	@Test
	@Order(3)
	void whenCreate_thenEntityCreated() {
		Room room = Room.builder().title("Small Room").size(25).build();
		roomRepository.save(room);

		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		animalRepository.save(animal);

		Favourite favourite = new Favourite(room, animal);
		assertNotNull(favouriteRepository.save(favourite));

		final Favourite favourite2 = new Favourite(room, animal);
		assertThrows(DataIntegrityViolationException.class, () -> {
			favouriteRepository.save(favourite2);
		});
	}

	@Test
	@Order(4)
	void whenDeleted_thenEntityDeleted() {
		Room room = Room.builder().title("Small Room").size(25).build();
		roomRepository.save(room);

		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		animalRepository.save(animal);

		Favourite favourite = new Favourite(room, animal);
		favouriteRepository.save(favourite);

		long favId = favourite.getId();
		favouriteRepository.deleteById(favId);

		assertTrue(favouriteRepository.findById(favId).isEmpty());
	}

	@Test
	@Order(5)
	void whenCreated_thenFindByAnimalAndOrRoom() {
		Room room = Room.builder().title("Small Room").size(25).build();
		roomRepository.save(room);

		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		animalRepository.save(animal);

		favouriteRepository.save(new Favourite(room, animal));

		Optional<Favourite> favourite = favouriteRepository.findByRoomAndAnimal(room, animal);

		assertTrue(favourite.isPresent());
		assertEquals(animal, favourite.get().getAnimal());
		assertEquals(room, favourite.get().getRoom());

		Room room1 = roomRepository.save(Room.builder().title("Big Room").size(30).build());
		favouriteRepository.save(new Favourite(room1, animal));

		List<Favourite> favouritesByAnimal = favouriteRepository.findByAnimal(animal);
		assertFalse(favouritesByAnimal.isEmpty());
		assertEquals(2, favouritesByAnimal.size());

		List<Room> rooms = favouritesByAnimal.stream().map(Favourite::getRoom).collect(Collectors.toList());
		assertTrue(rooms.contains(room));
		assertTrue(rooms.contains(room1));
	}

}
