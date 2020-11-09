/**
 * 
 */
package com.jayaprabahar.favouritezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : Favourite.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "roomId", "animalId" }) })
@Data
@NoArgsConstructor
public class Favourite {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "room_id", table = "Room")
	private long roomId;

	@Column(name = "animal_id", table = "Animal")
	private long animalId;

	public Favourite(long roomId, long animalId) {
		this.roomId = roomId;
		this.animalId = animalId;
	}

}
