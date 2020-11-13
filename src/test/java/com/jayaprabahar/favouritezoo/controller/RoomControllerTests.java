/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.favouritezoo.dto.RoomDto;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.service.RoomService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomControllerTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 13, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RoomControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private RoomController roomController;
	
	@MockBean
	private RoomService roomService;

	private JacksonTester<RoomDto> jsonRoomDto;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	void should_EmptyResponse_When_NoEntityExists() throws Exception {
		Mockito.when(roomService.findAllRooms(Pageable.unpaged())).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/rooms"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	@Test
	void should_ListResponse_When_EntitiesExists() throws Exception {
		Mockito.when(roomService.findByRoomId(1l)).thenReturn(Room.builder().title("Yellow").size(10l).id(1l).build());

		mockMvc.perform(get("/rooms/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.title", Matchers.equalTo("Yellow")))
				.andExpect(jsonPath("$.size", Matchers.equalTo(10)));
	}
	
	@Test
	void should_Create_When_Post() throws Exception {
		RoomDto newRoomDto = new RoomDto("Small Pink Room", 47L);
		Mockito.when(roomService.createRoom(newRoomDto)).thenReturn(Room.builder().title("Small Pink Room").size(47L).build());
		
		mockMvc.perform(post("/rooms")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonRoomDto.write(newRoomDto).getJson()))
				  .andExpect(status().isCreated())
				  .andExpect(jsonPath("$.title", Matchers.equalTo("Small Pink Room")))
				  .andExpect(jsonPath("$.size", Matchers.equalTo(47)));
	}
	
	@Test
	void should_Accept_When_Put() throws Exception {
		RoomDto newRoomDto = new RoomDto("Medium Red Room", 50l);
		Mockito.when(roomService.updateRoom(2l, newRoomDto)).thenReturn(Room.builder().title("Medium Red Room").size(50l).build());

		mockMvc.perform(put("/rooms/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRoomDto.write(new RoomDto("Medium Red Room", 50l)).getJson()))
				.andExpect(status().isAccepted());
	}
	
	@Test
	void should_SuccessDelete_When_DeletingNotExists() throws Exception {
		Mockito.doNothing()
			.doThrow(new RoomNotFoundException(1l))
			.when(roomService).deleteRoom(1l);

		mockMvc.perform(delete("/rooms/1"))
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", Matchers.equalTo(200)))
				.andExpect(jsonPath("$.message", Matchers.equalTo("Room with id 1 is deleted")));
	}

}
