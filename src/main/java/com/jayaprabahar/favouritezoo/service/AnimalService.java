/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalService.java </p>
 * <p> Description: Service layer for animal entities </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Service
public class AnimalService {

	private AnimalRepository animalRepository;

	/**
	 * 
	 */
	/**
	 * @param animalRepository
	 */
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	/**
	 * Creates Animal or throw exception
	 * 
	 * @param newAnimalDto
	 * @return Animal
	 */
	public Animal createAnimal(AnimalDto newAnimalDto) {
		return animalRepository.save(Animal.builder().title(newAnimalDto.getTitle()).type(newAnimalDto.getType())
				.preference(newAnimalDto.getPreference()).build());
	}

	/**
	 * Updates Animal entity or throw exception
	 * @param id
	 * @param newAnimalDto
	 * @return
	 */
	public Animal updateAnimal(Long id, AnimalDto newAnimalDto) {
		return animalRepository.findById(id).map(animal -> {
			animal.setTitle(newAnimalDto.getTitle());
			animal.setType(newAnimalDto.getType());
			animal.setPreference(newAnimalDto.getPreference());
			return animalRepository.save(animal);
		}).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	/**
	 * Deletes Animal entity or throw exception
	 * @param id
	 */
	public void deleteAnimal(Long id) {
		animalRepository.findById(id).ifPresentOrElse(e -> animalRepository.delete(e), () -> {
			throw new AnimalNotFoundException(id);
		});
	}

	/**
	 * Finds animal by Id
	 * 
	 * @param id
	 * @return Animal
	 */
	public Animal findAnimalById(Long id) {
		return animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	/**
	 * Finds All Animals and returns based on pageable sorting
	 * 
	 * @param pageable
	 * @return
	 */
	public List<AnimalResponseDto> findAllAnimals(Pageable pageable) {
		return animalRepository.findAll(pageable).toList().stream()
				.map(e -> AnimalResponseDto.builder().title(e.getTitle()).added(e.getAdded()).build())
				.collect(Collectors.toList());
	}

}
