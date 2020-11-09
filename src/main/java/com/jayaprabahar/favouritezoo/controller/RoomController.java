/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
@RequestMapping("/favouritezoo/rooms")
public class RoomController {

	private RoomService roomService;

	/**
	 * 
	 */
	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping
	public List<Room> findAllRooms() {
		return roomService.findAllRooms();
	}

	@GetMapping("/{roomId}")
	public Room findRoom(@PathVariable Long roomId) {
		return roomService.findRoomById(roomId);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Room createRoom(@RequestBody RoomDto roomDto) {
		return roomService.createRoom(roomDto);
	}

	@PutMapping("/{roomId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Room updateRoom(@PathVariable Long roomId, @RequestBody RoomDto roomDto) {
		return roomService.updateRoom(roomId, roomDto);
	}

	@DeleteMapping("/{roomId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRoom(@PathVariable Long roomId) {
		roomService.deleteRoom(roomId);
	}

}
