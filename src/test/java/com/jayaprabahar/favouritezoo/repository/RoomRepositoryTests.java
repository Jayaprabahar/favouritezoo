/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomRepositoryTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class RoomRepositoryTests {

	@Autowired
	private RoomRepository roomRepository;

	@Test
	@Order(1)
	void injectedComponentsAreNotNull() {
		assertThat(roomRepository).isNotNull();
	}

	@Test
	@Order(2)
	void whenNoEntries_thenResultIsEmpty() {
		assertTrue(roomRepository.findAll().isEmpty());
		assertTrue(roomRepository.findById(1l).isEmpty());
	}

	@Test
	@Order(3)
	void whenCreate_thenEntityCreated() {
		Room room = Room.builder().title("Small Room").size(25).build();
		roomRepository.save(room);

		assertEquals("Small Room", room.getTitle());
		assertEquals(25, room.getSize());

		assertFalse(roomRepository.findAll().isEmpty());
		assertFalse(roomRepository.findById(room.getId()).isEmpty());
		
		// When required data is not supplied
		final Room room2 = Room.builder().build();
		assertThrows(ConstraintViolationException.class, () -> {
			roomRepository.save(room2);
		});
		
		final Room room3 = Room.builder().size(10l).build();
		assertThrows(ConstraintViolationException.class, () -> {
			roomRepository.save(room3);
		});
	}

	@Test
	@Order(4)
	void whenUpdate_thenEntityUpdated() {
		Room room = Room.builder().title("Small Room").size(25).build();
		roomRepository.save(room);

		room.setTitle("Big Room");
		room.setSize(40);
		roomRepository.save(room);

		assertEquals("Big Room", room.getTitle());
		assertEquals(40, room.getSize());

		assertFalse(roomRepository.findAll().isEmpty());
		assertFalse(roomRepository.findById(room.getId()).isEmpty());
	}

	@Test
	@Order(5)
	void whenDelete_thenEntityDeleted() {
		Room room = Room.builder().title("Medium Room").size(30).build();

		roomRepository.save(room);
		roomRepository.deleteById(room.getId());
		assertTrue(roomRepository.findById(room.getId()).isEmpty());
	}

}
