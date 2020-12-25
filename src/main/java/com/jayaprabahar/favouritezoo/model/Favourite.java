package com.jayaprabahar.favouritezoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : Favourite.java </p>
 * <p> Description: Favourite Entity  </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "room_id", "animal_id" }) })
@Data
@NoArgsConstructor
public class Favourite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "room_id")
	@NotNull
	private Room room;

	@OneToOne
	@NotNull
	@JoinColumn(name = "animal_id")
	private Animal animal;

	/**
	 * @param room Room object
	 * @param animal Animal object
	 */
	public Favourite(Room room, Animal animal) {
		this.room = room;
		this.animal = animal;
	}
	
	public Favourite(Animal animal) {
		this.animal = animal;
	}
}
