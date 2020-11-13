/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.FavouriteNotFoundException;
import com.jayaprabahar.favouritezoo.errorhandling.RoomNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.service.FavouriteService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteControllerTests.java </p>
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
class FavouriteControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private FavouriteController favouriteController;
	
	@MockBean
	private FavouriteService favouriteService;
	
	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	

	@Test
	void should_FindAssigned_When_FavouriteExists() throws Exception {
		Room room = Room.builder().title("Small Room").size(25).build();
		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		
		Mockito.when(favouriteService.findFavourite(1l, 1l)).thenReturn(new Favourite(room, animal));

		mockMvc.perform(get("/favourite/rooms/1/animals/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.equalTo(0)))
			.andExpect(jsonPath("$.room.title", Matchers.equalTo("Small Room")))
			.andExpect(jsonPath("$.room.size", Matchers.equalTo(25)))
			
			.andExpect(jsonPath("$.animal.title", Matchers.equalTo("Cow")))
			.andExpect(jsonPath("$.animal.type", Matchers.equalTo("<=")))
			.andExpect(jsonPath("$.animal.preference", Matchers.equalTo(30)));
	}
	
	@Test
	void should_NotFoundException_When_NoNeededExists() throws Exception {
		Mockito.when(favouriteService.findFavourite(1l, 1l)).thenThrow(new RoomNotFoundException(1l));

		mockMvc.perform(get("/favourite/rooms/1/animals/1").contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isNotFound())
		      .andExpect(result -> assertTrue(result.getResolvedException() instanceof RoomNotFoundException));
		
		Mockito.when(favouriteService.findFavourite(2l, 2l)).thenThrow(new AnimalNotFoundException(2l));

		mockMvc.perform(get("/favourite/rooms/2/animals/2").contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isNotFound())
		      .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnimalNotFoundException));
		
		Mockito.when(favouriteService.findFavourite(3l, 3l)).thenThrow(new FavouriteNotFoundException(3l, 3l));

		mockMvc.perform(get("/favourite/rooms/3/animals/3").contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isNotFound())
		      .andExpect(result -> assertTrue(result.getResolvedException() instanceof FavouriteNotFoundException));
	}
	
	@Test
	void should_GetRoomNames_When_FavouriteRoomsForAnimalExists() throws Exception {
		Mockito.when(favouriteService.getFavouriteRoomsByAnimalId(5l)).thenReturn(List.of("Yellow"));
		
		mockMvc.perform(get("/favourite/animals/5"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(1)))
			.andExpect(jsonPath("$", Matchers.contains("Yellow")));
	}
	
	@Test
	void should_GenericResponse_When_FavouriteDeleted() throws Exception {
		Mockito.doNothing().when(favouriteService).deleteFavourite(5l, 6l);
		
		mockMvc.perform(delete("/favourite/rooms/5/animals/6"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", Matchers.equalTo("Favourite room is unassigned for room 5 and animal 6")));
	}
	
}
