/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomServiceTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
class RoomServiceTests {

	@Autowired
	private RoomService roomService;

	// RoomRepositoryTests covered all CRUD operations.
	// So here we are going to focus only RoomDto and exception scenarios which are
	// written extra in RoomService

	@Test
	void testCreateWithRoomDto() {
		final RoomDto newRoomDto = new RoomDto(null, 0l);
		assertThrows(ConstraintViolationException.class, () -> {
			roomService.createRoom(newRoomDto);
		});

		final RoomDto newRoomDto2 = new RoomDto("Blue", 25l);
		Room room = roomService.createRoom(newRoomDto2);

		assertEquals("Blue", room.getTitle());
		assertEquals(25l, room.getSize());
		assertTrue(room.getId() >= 1);
	}

	@Test
	void testRoomNotFoundException() {
		long lastGeneratedPrimaryKey = roomService.createRoom(new RoomDto("Red", 35l)).getId();

		assertThrows(RoomNotFoundException.class, () -> {
			roomService.findByRoomId(lastGeneratedPrimaryKey + 1);
		});

		final RoomDto newRoomDto2 = new RoomDto("Blue", 25l);
		assertThrows(RoomNotFoundException.class, () -> {
			roomService.updateRoom(lastGeneratedPrimaryKey + 1, newRoomDto2);
		});

		roomService.updateRoom(lastGeneratedPrimaryKey, newRoomDto2);

		assertThrows(RoomNotFoundException.class, () -> {
			roomService.deleteRoom(lastGeneratedPrimaryKey + 1);
		});

		roomService.deleteRoom(lastGeneratedPrimaryKey);
	}

}
