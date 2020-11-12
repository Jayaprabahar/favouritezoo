/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalServiceTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
class AnimalServiceTests {

	@Autowired
	private AnimalService animalService;

	// AnimalRepositoryTests covered all CRUD operations.
	// So here I added AnimalDto, AnimalResponseDto and AnimalNotFoundException
	// scenarios which are written extra in animalService

	@Test
	void testCreateWithAnimalDto() {
		final AnimalDto newAnimalDto = new AnimalDto("Cow", null, 0l);
		assertThrows(ConstraintViolationException.class, () -> {
			animalService.createAnimal(newAnimalDto);
		});

		final AnimalDto newAnimalDto2 = new AnimalDto("New Cow", ">=", 20l);
		Animal animal = animalService.createAnimal(newAnimalDto2);

		assertEquals("New Cow", animal.getTitle());
		assertEquals(">=", animal.getType());
		assertEquals(20l, animal.getPreference());
		assertTrue(animal.getId() >= 1);
	}

	@Test
	void testAnimalNotFoundException() {
		long lastGeneratedPrimaryKey = animalService.createAnimal(new AnimalDto("New Cow2", ">=", 20l)).getId();

		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.findAnimalById(lastGeneratedPrimaryKey + 1);
		});

		animalService.findAnimalById(lastGeneratedPrimaryKey);

		final AnimalDto newAnimalDto2 = new AnimalDto("Cat", "<=", 8l);
		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.updateAnimal(lastGeneratedPrimaryKey + 1, newAnimalDto2);
		});

		animalService.updateAnimal(lastGeneratedPrimaryKey, newAnimalDto2);

		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.deleteAnimal(lastGeneratedPrimaryKey + 1);
		});
		animalService.deleteAnimal(lastGeneratedPrimaryKey);
	}

	@Test
	void testFindAllAnimals() {
		animalService.createAnimal(new AnimalDto("Small Cat", "<=", 10l));
		animalService.createAnimal(new AnimalDto("Big Cat", "<=", 20l));
		List<AnimalResponseDto> animalResponseDtos = animalService.findAllAnimals(Pageable.unpaged());

		assertTrue(animalResponseDtos.size() >= 2);
		assertTrue(CollectionUtils.containsAll(animalResponseDtos.stream().map(AnimalResponseDto::getTitle).collect(Collectors.toList()), List.of("Small Cat", "Big Cat")));
	}
}
