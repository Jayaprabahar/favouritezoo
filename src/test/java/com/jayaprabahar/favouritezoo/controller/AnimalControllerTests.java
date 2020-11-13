/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.jayaprabahar.favouritezoo.service.AnimalService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalControllerTests.java </p>
 * <p> Description: AnimalControllerTests </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AnimalControllerTests {

	/**
	 * @throws Exception 
	 * 
	 */
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private AnimalController animalController;
	
	@MockBean
	private AnimalService animalService;

	private JacksonTester<AnimalDto> jsonAnimalDto;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void should_RetrieveById_When_Exists() throws Exception {
		Mockito.when(animalService.findAnimalById(1l)).thenReturn(Animal.builder().title("Cat").type(">=").preference(34).build());

		mockMvc.perform(get("/animals/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", Matchers.equalTo("Cat")))
				.andExpect(jsonPath("$.type", Matchers.equalTo(">=")))
				.andExpect(jsonPath("$.preference", Matchers.equalTo(34)));
	}
	
	@Test
	void should_AnimalNotFoundException_When_NotExists() throws Exception {
		Mockito.when(animalService.findAnimalById(1l)).thenThrow(AnimalNotFoundException.class);

		mockMvc.perform(get("/animals/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnimalNotFoundException));
	}
	
	@Test
	void should_Create_When_Post() throws Exception {
		AnimalDto newAnimalDto = new AnimalDto("dOG", "==", 47);
		Mockito.when(animalService.createAnimal(newAnimalDto)).thenReturn(Animal.builder().title("dOG").type("==").preference(47).build());
		
		mockMvc.perform(post("/animals")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonAnimalDto.write(newAnimalDto).getJson()))
				  .andExpect(status().isCreated())
				  .andExpect(jsonPath("$.title", Matchers.equalTo("dOG")))
				  .andExpect(jsonPath("$.type", Matchers.equalTo("==")))
				  .andExpect(jsonPath("$.preference", Matchers.equalTo(47)));
	}
	
	@Test
	void should_Accept_When_Put() throws Exception {
		AnimalDto newAnimalDto = new AnimalDto("Elephant", ">", 50);
		Mockito.when(animalService.updateAnimal(2l, newAnimalDto)).thenReturn(Animal.builder().title("Elephant1").type(">").preference(50).build());

		mockMvc.perform(put("/animals/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAnimalDto.write(new AnimalDto("Elephant", ">", 50)).getJson()))
				.andExpect(status().isAccepted());
	}
	
	@Test
	void should_SuccessDelete_When_DeletingNotExists() throws Exception {
		Mockito.doNothing()
			.doThrow(new AnimalNotFoundException(1l))
			.when(animalService).deleteAnimal(1l);

		mockMvc.perform(delete("/animals/1"))
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", Matchers.equalTo(200)))
				.andExpect(jsonPath("$.message", Matchers.equalTo("Animal with id 1 is deleted")));
	}

}
