/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayaprabahar.favouritezoo.model.Favourite;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteRepository.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

	/**
	 * 
	 */
//	Optional<Favourite> findByRoomIdAndAnimalId(long roomId, long animalId);
//	
//	List<Favourite> findAllByAnimalId(long animalId);
//	
//	List<Favourite> findAllByRoomIdId(long roomlId);
}
