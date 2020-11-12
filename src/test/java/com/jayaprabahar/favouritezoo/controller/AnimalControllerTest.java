/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

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
import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalControllerTest.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AnimalControllerTest {

	/**
	 * @throws Exception 
	 * 
	 */
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private AnimalController animalController;

	@MockBean
	private AnimalRepository animalRepository;

	private JacksonTester<AnimalDto> jsonAnimalDto;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void canRetrieveByIdWhenExists() throws Exception {
		Mockito.when(animalRepository.findById(1l)).thenReturn(Optional.of(Animal.builder().title("Cat").type(">=").preference(34).build()));

		mockMvc.perform(get("/animals/1"))
				.andExpect(status().isOk())
				//.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$.title", Matchers.equalTo("Cat")))
				.andExpect(jsonPath("$.type", Matchers.equalTo(">=")))
				.andExpect(jsonPath("$.preference", Matchers.equalTo(34)));
	}
	
	@Test
	void getAnimalNotFoundExceptionWhenEntityNotExists() throws Exception {
		Mockito.when(animalRepository.findById(1l)).thenReturn(Optional.empty());

		mockMvc.perform(get("/animals/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnimalNotFoundException))
			      .andExpect(result -> assertEquals("Could not find animal with id 1", result.getResolvedException().getMessage()));
	}
	
	@Test
	void getAnimalNotFoundExceptionWhenEntityNotExist1s() throws Exception {
//		Mockito.when(animalRepository.findById(1l)).thenReturn(Optional.of(Animal.builder().title("Cat").type(">=").preference(34).build()));

		mockMvc.perform(post("/animals/1")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content(jsonAnimalDto.write(new AnimalDto("Cat", ">=", 34)).getJson()))
			      .andExpect(status().isCreated())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnimalNotFoundException))
			      .andExpect(result -> assertEquals("Could not find animal with id 1", result.getResolvedException().getMessage()));
	}
}
