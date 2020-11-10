/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.repository.RoomRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomService.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Service
public class RoomService {

	private RoomRepository roomRepository;

	/**
	 * 
	 */
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public List<Room> findAllRooms(Pageable pageable) {
		return roomRepository.findAll(pageable).toList();
	}

	public Room findRoomById(Long roomId) {
		return roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
	}

	public Room createRoom(RoomDto newRoomDto) {
		return roomRepository.save(Room.builder().title(newRoomDto.getTitle()).size(newRoomDto.getSize()).build());
	}

	public Room updateRoom(Long id, RoomDto newRoomDto) {
		return roomRepository.findById(id).map(room -> {
			room.setTitle(newRoomDto.getTitle());
			room.setSize(newRoomDto.getSize());
			return roomRepository.save(room);
		}).orElseThrow(() -> new RoomNotFoundException(id));
	}

	public void deleteRoom(Long roomId) {
		roomRepository.findById(roomId).ifPresentOrElse(e -> roomRepository.delete(e), () -> {
			throw new RoomNotFoundException(roomId);
		});
	}

}
