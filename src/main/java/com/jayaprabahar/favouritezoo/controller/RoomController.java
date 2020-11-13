/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jayaprabahar.favouritezoo.dto.GenericResponseDto;
import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.service.RoomService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomController.java </p>
 * <p> Description: Controller class for room entity </p>
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

	private RoomService roomService;

	/**
	 * 
	 */
	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * Finds all rooms
	 * 
	 * @param pageable sorting enabled
	 * @return List<Room> 
	 */
	@GetMapping
	public List<Room> findAllRooms(Pageable pageable) {
		return roomService.findAllRooms(pageable);
	}

	/**
	 * FindRoom by roomId
	 * 
	 * @param roomId
	 * @return Room
	 */
	@GetMapping("/{roomId}")
	public Room findRoom(@PathVariable final Long roomId) {
		return roomService.findByRoomId(roomId);
	}

	/**
	 * Creates room
	 * 
	 * @param roomDto
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
	 * @param roomId
	 * @param roomDto
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
	 * @param roomId
	 * @return GenericResponseDto
	 */
	@DeleteMapping("/{roomId}")
	public ResponseEntity<GenericResponseDto> deleteRoom(@PathVariable final Long roomId) {
		roomService.deleteRoom(roomId);
		return new ResponseEntity<>(GenericResponseDto.builder().message(String.format("Room with id %d is deleted", roomId)).status(200).build(), HttpStatus.OK);
	}

}
