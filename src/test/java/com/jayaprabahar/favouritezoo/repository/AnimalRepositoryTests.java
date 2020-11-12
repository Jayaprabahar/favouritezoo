/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jayaprabahar.favouritezoo.model.Animal;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRepositoryTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class AnimalRepositoryTests {

	@Autowired
	private AnimalRepository animalRepository;

	@Test
	@Order(0)
	void injectedComponentsAreNotNull() {
		assertThat(animalRepository).isNotNull();
	}

	@Test
	@Order(1)
	void whenNoEntries_thenResultIsEmpty() {
		// On load list is empty & no entry
		assertTrue(animalRepository.findAll().isEmpty());
		assertTrue(animalRepository.findById(1l).isEmpty());
	}

	@Test
	@Order(2)
	void whenCreate_thenEntityCreated() {
		LocalDateTime beforeTime = LocalDateTime.now();

		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		animalRepository.save(animal);

		assertEquals("Cow", animal.getTitle());
		assertEquals("<=", animal.getType());
		assertEquals(30, animal.getPreference());
		assertEquals(null, animal.getRoom());

		LocalDateTime laterTime = LocalDateTime.now();

		assertTrue((laterTime.isEqual(animal.getAdded()) || laterTime.isAfter(animal.getAdded())) && (beforeTime.isEqual(animal.getAdded()) || beforeTime.isBefore(animal.getAdded())));

		assertFalse(animalRepository.findAll().isEmpty());
		assertFalse(animalRepository.findById(animal.getId()).isEmpty());

		// When required data is not supplied
		final Animal animal2 = Animal.builder().build();
		assertThrows(ConstraintViolationException.class, () -> {
			animalRepository.save(animal2);
		});

		final Animal animal3 = Animal.builder().preference(10l).build();
		assertThrows(ConstraintViolationException.class, () -> {
			animalRepository.save(animal3);
		});
	}

	@Test
	@Order(3)
	void whenUpdate_thenEntityUpdated() {
		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();
		animalRepository.save(animal);

		animal.setTitle("New Cow");
		animalRepository.save(animal);

		assertEquals("New Cow", animal.getTitle());
		assertEquals("<=", animal.getType());
		assertEquals(30, animal.getPreference());
		assertEquals(null, animal.getRoom());

		assertFalse(animalRepository.findAll().isEmpty());
		assertFalse(animalRepository.findById(animal.getId()).isEmpty());
	}

	@Test
	@Order(4)
	void whenDelete_thenEntityDeleted() {
		Animal animal = Animal.builder().title("Cow").type("<=").preference(30).build();

		animalRepository.save(animal);
		animalRepository.deleteById(animal.getId());
		assertTrue(animalRepository.findById(animal.getId()).isEmpty());
	}

}
