package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.service.RoomService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomController.java </p>
 * <p> Description: Controller class for room entity related endpoints </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
@RequestMapping("/rooms")
@Validated
public class RoomController {

	private final RoomService roomService;

	/**
	 * 
	 */
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * Finds all rooms with pageable sorting and filtering options
	 * 
	 * @param pageable sorting enabled
	 * @return List<Room> 
	 */
	@GetMapping
	public List<Room> findAllRooms(Pageable pageable) {
		return roomService.findAllRooms(pageable);
	}

	/**
	 * Finds Room by roomId
	 * 
	 * @param roomId Long
	 * @return Room
	 */
	@GetMapping("/{roomId}")
	public Room findRoom(@PathVariable final Long roomId) {
		return roomService.findByRoomId(roomId);
	}

	/**
	 * Creates room
	 * 
	 * @param roomDto RoomDto
	 * @return Room
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Room createRoom(@RequestBody @Valid final RoomDto roomDto) {
		return roomService.createRoom(roomDto);
	}

	/**
	 * Updates room by roomId
	 * 
	 * @param roomId Long
	 * @param roomDto RoomDto
	 * @return Room
	 */
	@PutMapping("/{roomId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Room updateRoom(@PathVariable Long roomId, @Valid @RequestBody final RoomDto roomDto) {
		return roomService.updateRoom(roomId, roomDto);
	}

	/**
	 * Deletes room by roomId
	 * 
	 * @param roomId Long
	 * @return GenericResponseDto
	 */
	@DeleteMapping("/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable final Long roomId) {
		roomService.deleteRoom(roomId);
		return new ResponseEntity<>(String.format("Room with id %d is deleted", roomId), HttpStatus.NO_CONTENT);
	}

}
