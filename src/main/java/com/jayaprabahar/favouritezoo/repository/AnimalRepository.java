/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalRepository.java </p>
 * <p> Description: Repository class for Animal</p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	Page<Animal> findAllById(Long animalId, Pageable pageable);

	Page<Animal> findAllByRoom(Room room, Pageable pageable);

}
