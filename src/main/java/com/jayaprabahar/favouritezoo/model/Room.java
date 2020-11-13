package com.jayaprabahar.favouritezoo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : Room.java </p>
 * <p> Description: Room Entity </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private long id;

	@NotNull
	private String title;

	@NotNull
	private long size;

	@CreationTimestamp
	private LocalDateTime created;

	@OneToMany(targetEntity = Animal.class)
	private List<Animal> animals;

}
