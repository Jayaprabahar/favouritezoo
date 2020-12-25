package com.jayaprabahar.favouritezoo.repository;

import com.jayaprabahar.favouritezoo.model.Animal;
import com.jayaprabahar.favouritezoo.model.Favourite;
import com.jayaprabahar.favouritezoo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteRepository.java </p>
 * <p> Description: Repository class for Favourite </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
	
	Optional<Favourite> findByRoomAndAnimal(Room room, Animal animal);

	List<Favourite> findByAnimal(Animal animal);
}
