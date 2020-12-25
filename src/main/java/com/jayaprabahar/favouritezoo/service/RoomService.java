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
 * <p> Description: Service layer for Room entities</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Service
public class RoomService {

	private final RoomRepository roomRepository;

	/**
	 * 
	 */
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	/**
	 * Finds all rooms and performs sorting if provided
	 * 
	 * @param pageable Pageable
	 * @return List of Rooms
	 */
	public List<Room> findAllRooms(Pageable pageable) {
		return roomRepository.findAll(pageable).toList();
	}

	/**
	 * Finds rooms by id, if exist
	 * 
	 * @param roomId Room Id
	 * @return Room
	 */
	public Room findByRoomId(Long roomId) {
		return roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
	}

	/**
	 * Creates room base on Room request DTO
	 * 
	 * @param newRoomDto New RoomDto
	 * @return Room Created Room entity
	 */
	public Room createRoom(RoomDto newRoomDto) {
		return roomRepository.save(Room.builder().title(newRoomDto.getTitle()).size(newRoomDto.getSize()).build());
	}

	/**
	 * Updates room base on Room request DTO
	 * 
	 * @param id Room Id
	 * @param newRoomDto new RoomDto
	 * @return Room Updated Room entity
	 */
	public Room updateRoom(Long id, RoomDto newRoomDto) {
		return roomRepository.findById(id).map(room -> {
			room.setTitle(newRoomDto.getTitle());
			room.setSize(newRoomDto.getSize());
			return roomRepository.save(room);
		}).orElseThrow(() -> new RoomNotFoundException(id));
	}

	/**
	 * Deletes room base on roomId
	 * 
	 * @param roomId Room Id
	 */
	public void deleteRoom(Long roomId) {
		roomRepository
				.findById(roomId)
				.ifPresentOrElse(roomRepository::delete,
				() -> { throw new RoomNotFoundException(roomId); });
	}

}
