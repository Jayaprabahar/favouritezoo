/**
 * 
 */
package com.jayaprabahar.favouritezoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayaprabahar.favouritezoo.model.Room;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomRepository.java </p>
 * <p> Description: Repository class for Room </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
