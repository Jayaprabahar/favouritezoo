package com.jayaprabahar.favouritezoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Room;
import com.jayaprabahar.favouritezoo.service.AnimalRoomService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRoomControllerTests.java </p>
 * <p> Description: Tests AnimalRoomController class </p>
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
class AnimalRoomControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnimalRoomService animalRoomService;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void should_AddRoom_When_AssignedRoom() throws Exception {
		Mockito.when(animalRoomService.addRoomForAnimal(1L, 1L))
				.thenReturn(Animal.builder().title("Cat").type(">=").preference(34).room(Room.builder().id(1L).title("Blue").size(35L).build()).build());

		mockMvc.perform(patch("/rooms/1/animals/1")).andExpect(status().isOk()).andExpect(jsonPath("$.title", Matchers.equalTo("Cat"))).andExpect(jsonPath("$.type", Matchers.equalTo(">=")))
				.andExpect(jsonPath("$.preference", Matchers.equalTo(34))).andExpect(jsonPath("$.room.id", Matchers.equalTo(1))).andExpect(jsonPath("$.room.title", Matchers.equalTo("Blue")))
				.andExpect(jsonPath("$.room.size", Matchers.equalTo(35)));
	}

	@Test
	void should_UpdateRoom_When_MovedRoom() throws Exception {
		Mockito.when(animalRoomService.updateNewRoomForAnimal(1L, 1L, 2L))
				.thenReturn(Animal.builder().title("Cat").type(">=").preference(34).room(Room.builder().id(2L).title("Blue").size(35L).build()).build());

		mockMvc.perform(patch("/rooms/1/animals/1/2")).andExpect(status().isOk()).andExpect(jsonPath("$.title", Matchers.equalTo("Cat"))).andExpect(jsonPath("$.type", Matchers.equalTo(">=")))
				.andExpect(jsonPath("$.preference", Matchers.equalTo(34))).andExpect(jsonPath("$.room.id", Matchers.equalTo(2))).andExpect(jsonPath("$.room.title", Matchers.equalTo("Blue")))
				.andExpect(jsonPath("$.room.size", Matchers.equalTo(35)));
	}

	@Test
	void testHappyAnimals() throws Exception {
		Map<String, Long> happyAnimalMap = Map.of("Big Blue Room", 4L, "Medium violet Room", 3L, "Small pink Room", 2L, "Smallest purple Room", 1L);
		Mockito.when(animalRoomService.getHappyAnimalsListPerRoom()).thenReturn(happyAnimalMap);

		MvcResult result = mockMvc.perform(get("/happyAnimals")).andExpect(status().isOk()).andReturn();

		Map<Object, Object> orderMap = Arrays.stream(StringUtils.strip(result.getResponse().getContentAsString(), "{}").split(",")).map(str -> str.split(":"))
				.collect(Collectors.toMap(str -> StringUtils.strip(str[0], "\""), str -> Integer.parseInt(str[1])));

		assertTrue(CollectionUtils.disjunction(happyAnimalMap.keySet(), orderMap.keySet()).isEmpty());

	}

}
