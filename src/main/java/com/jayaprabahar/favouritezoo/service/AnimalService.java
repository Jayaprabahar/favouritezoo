/**
 * 
 */
package com.jayaprabahar.favouritezoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jayaprabahar.favouritezoo.dto.AnimalDto;
import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import com.jayaprabahar.favouritezoo.errorhandling.AnimalNotFoundException;
import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.repository.AnimalRepository;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalService.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
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
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	public List<Animal> findAllAnimals(String sortingVaraible, String sortingOrder) {
		return animalRepository.findAll(Sort.by(Direction.fromString(sortingOrder), sortingVaraible));
	}

	public Animal findAnimalById(Long id) {
		return animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	public Animal createAnimal(AnimalDto newAnimalDto) {
		return animalRepository.save(Animal.builder().title(newAnimalDto.getTitle()).type(newAnimalDto.getType()).preference(newAnimalDto.getPreference()).build());
	}

	public Animal updateAnimal(Long id, AnimalDto newAnimalDto) {
		return animalRepository.findById(id).map(animal -> {
			animal.setTitle(newAnimalDto.getTitle());
			animal.setType(newAnimalDto.getType());
			animal.setPreference(newAnimalDto.getPreference());
			return animalRepository.save(animal);
		}).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	public void deleteAnimal(Long id) {
		animalRepository.findById(id).ifPresentOrElse(e -> animalRepository.delete(e), () -> {
			throw new AnimalNotFoundException(id);
		});
	}

	/**
	 * @param pageable
	 * @return
	 */
	public List<AnimalResponseDto> findAllAnimals(Pageable pageable) {
		return animalRepository.findAll(pageable).toList().stream()
				.map(e -> AnimalResponseDto.builder().title(e.getTitle()).added(e.getAdded()).build())
				.collect(Collectors.toList());
	}

}
