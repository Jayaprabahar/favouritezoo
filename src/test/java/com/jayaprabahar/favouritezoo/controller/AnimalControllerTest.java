/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;


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
class AnimalControllerTest {
	
	private static final String API_ANIMAL_URL = "/animals";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	void testListAllAnimals() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(API_ANIMAL_URL))
				.andExpect(status().isOk())
				.andReturn();
		
		List<AnimalResponseDto> animalResponseDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
		
		System.out.println("1-->" + animalResponseDtos);
		System.out.println("2-->" + animalResponseDtos.get(0));
		System.out.println("3-->" + animalResponseDtos.get(0).getTitle());
	}
	
	@Test
	void testListAllAnimals1() throws Exception {
		String body = "[{\"title\":\"Dog\",\"added\":\"2020-11-11T18:01:09.251246\"},{\"title\":\"Cat\",\"added\":\"2020-11-11T18:01:09.458435\"}]";
		
		MvcResult mvcResult = mockMvc.perform(get(API_ANIMAL_URL))
				.andExpect(status().isOk())
				.andReturn();
		
		List<AnimalResponseDto> animalResponseDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
		
		System.out.println("1-->" + animalResponseDtos);
		System.out.println("2-->" + animalResponseDtos.get(0));
		System.out.println("3-->" + animalResponseDtos.get(0).getTitle());
		
//		MockHttpServletResponse:
//	           Status = 200
//	    Error message = null
//	          Headers = [Content-Type:"application/json"]
//	     Content type = application/json
//	             
//	    Forwarded URL = null
//	   Redirected URL = null
	}

//	@GetMapping("/{animalId}")
//	public Animal findAnimal(@PathVariable final Long animalId) {
//		return animalService.findAnimalById(animalId);
//	}
//
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Animal createAnimal(@RequestBody @Valid final AnimalDto newAnimalDto) {
//		return animalService.createAnimal(newAnimalDto);
//	}
//
//	@PutMapping("/{animalId}")
//	@ResponseStatus(HttpStatus.ACCEPTED)
//	public Animal updateAnimal(@PathVariable Long animalId, @Valid @RequestBody final AnimalDto newAnimalDto) {
//		return animalService.updateAnimal(animalId, newAnimalDto);
//	}
//
//	@DeleteMapping("/{animalId}")
//	public ResponseEntity<GenericResponseDto> deleteAnimal(@PathVariable final Long animalId) {
//		animalService.deleteAnimal(animalId);
//		return new ResponseEntity<>(GenericResponseDto.builder().message(String.format("Animal with id %d is deleted", animalId)).build(), HttpStatus.OK);
//	}
//
//	@GetMapping
//	public List<AnimalResponseDto> listAllAnimals(Pageable pageable) {
//		return animalService.findAllAnimals(pageable);
//	}

//	@Test
//	public void givenNonIntegerUpperLimit_thenStatus400_NotIntegerError() throws Exception {
//		Exception response = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "I_AM_NOT_A_NUMBER"))
//				.andExpect(status().isBadRequest())
//				.andReturn()
//				.getResolvedException();
//		assertTrue(response.getCause() instanceof NumberFormatException);
//		assertThat(response.getMessage(), containsString("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'"));
//	}
//
//	@Test
//	public void givenNegativeNumberUpperLimit_thenStatus400_OnlyPositiveNumbersAllowedError() throws Exception {
//		Exception response = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "-12"))
//				.andExpect(status().isBadRequest())
//				.andReturn()
//				.getResolvedException();
//		assertTrue(response instanceof ConstraintViolationException);
//		assertThat(response.getMessage(), containsString("Only positive numbers are allowed"));
//	}
//
//	@Test
//	public void givenBeyondRangePositiveUpperLimit_thenStatus400_BeyondAllowedUpperLimitError() throws Exception {
//		Exception response = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "1212"))
//				.andExpect(status().isBadRequest())
//				.andReturn()
//				.getResolvedException();
//		assertTrue(response instanceof UpperLimitReachedException);
//		assertThat(response.getMessage(), containsString("Allowed upper limit is " + allowedMaxUpperLimit));
//	}
//
//	@Test
//	public void givenPositiveCorrectRangeUpperLimit_thenStatus201() throws Exception {
//		mockMvc.perform(post(API_URL).param("upperLimit", "12"))
//		.andExpect(status().isCreated())
//		.andReturn();
//	}
//
//	@Test
//	public void givenXMLAcceptHeaderWithPositiveCorrectRangeUpperLimit_thenNoChangeInResponse() throws Exception {
//		mockMvc.perform(post(API_URL).accept(MediaType.APPLICATION_XML)
//				.param("upperLimit", "12"))
//		.andExpect(status().isCreated());
//	}
//
//	@Test
//	public void givenCorrectRangeUpperLimitSet_GetApiCall_thenSuccessResponse() throws Exception {
//		HttpSession session = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "12"))
//				.andExpect(status().isCreated())
//				.andReturn().getRequest()
//				.getSession();
//
//		MvcResult response = mockMvc.perform(get(API_URL)
//				.session((MockHttpSession) session))
//				.andExpect(status().isOk())
//		        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.upperLimit", is(12)))
//		        .andExpect(jsonPath("$.smallestdividablenumber", is(27720)))
//		        .andExpect(jsonPath("$.timeTakenInMillis", isA(Integer.class)))
//		        .andExpect(jsonPath("$.timeTakenInMillis", notNullValue())).andReturn();
//		assertThat(response.getResponse().getContentAsString(), containsString("{\"upperLimit\":12,\"smallestdividablenumber\":27720,\"timeTakenInMillis\":"));
//	}
//	
//	@Test
//	public void givenCorrectRangeUpperLimitSet_GetApiCallWithXMLAcceptHeader_thenSuccessResponse() throws Exception {
//		HttpSession session = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "20"))
//				.andExpect(status().isCreated())
//				.andReturn().getRequest()
//				.getSession();
//
//		MvcResult response = mockMvc.perform(get(API_URL).accept(MediaType.APPLICATION_XML)
//				.session((MockHttpSession) session))
//				.andExpect(status().isOk())
//		        .andExpect(content().contentType(MediaType.APPLICATION_XML))
//		        .andExpect(xpath("Response/upperLimit").number(20d))
//		        .andExpect(xpath("Response/smallestdividablenumber").number(232792560d))
//		        .andExpect(xpath("Response/timeTakenInMillis").exists())
//		        .andReturn();
//		assertThat(response.getResponse().getContentAsString(), containsString("<Response><upperLimit>20</upperLimit><smallestdividablenumber>232792560</smallestdividablenumber><timeTakenInMillis>"));
//	}
//	
//	@Test
//	public void givenUpperLimitNotSet_GetApiCall_thenPreconditionFailedError() throws Exception {
//		MvcResult postResponse = mockMvc.perform(post(API_URL).param("upperLimit", "1212"))
//				.andExpect(status().isBadRequest()).andReturn();
//		HttpSession session = postResponse.getRequest().getSession();
//
//		Exception getResponse = mockMvc.perform(get(API_URL).session((MockHttpSession) session))
//				.andExpect(status().isPreconditionFailed())
//				.andReturn()
//				.getResolvedException();
//		assertTrue(getResponse instanceof UpperLimitNotSetException);
//		assertThat(getResponse.getMessage(), containsString("Upper limit is not set"));
//	}
//
//	@Test
//	public void givenCorrectRangeUpperLimitSet_GetApiCallWithNotAllowedAcceptHeader_thenStatus406() throws Exception {
//		HttpSession session = mockMvc.perform(post(API_URL)
//				.param("upperLimit", "20"))
//				.andExpect(status().isCreated())
//				.andReturn().getRequest()
//				.getSession();
//		
//		mockMvc.perform(get(API_URL)
//				.accept(MediaType.APPLICATION_PDF).session((MockHttpSession) session))
//		.andExpect(status().isNotAcceptable());
//	}

	/* @formatter:on */



}
