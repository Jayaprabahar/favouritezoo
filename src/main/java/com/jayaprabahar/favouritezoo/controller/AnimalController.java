/**
 * 
 */
package com.jayaprabahar.favouritezoo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.dto.GenericResponseDto;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.service.AnimalService;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalController.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

	private AnimalService animalService;

	/**
	 * 
	 */
	@Autowired
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@GetMapping("/{animalId}")
	public Animal findAnimal(@PathVariable final Long animalId) {
		return animalService.findAnimalById(animalId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Animal createAnimal(@RequestBody @Valid final AnimalDto newAnimalDto) {
		return animalService.createAnimal(newAnimalDto);
	}

	@PutMapping("/{animalId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Animal updateAnimal(@PathVariable Long animalId, @Valid @RequestBody final AnimalDto newAnimalDto) {
		return animalService.updateAnimal(animalId, newAnimalDto);
	}

	@DeleteMapping("/{animalId}")
	public ResponseEntity<GenericResponseDto> deleteAnimal(@PathVariable final Long animalId) {
		animalService.deleteAnimal(animalId);
		return new ResponseEntity<>(GenericResponseDto.builder().message(String.format("Animal with id %d is deleted", animalId)).build(), HttpStatus.OK);
	}

	@GetMapping
	public List<AnimalResponseDto> listAllAnimals(Pageable pageable) {
		return animalService.findAllAnimals(pageable);
	}

}
