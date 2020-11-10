/**
 * 
 */
package com.jayaprabahar.favouritezoo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : Animal.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
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
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "animal_id")
	private long id;

	private String title;

	@UpdateTimestamp
	private LocalDateTime located;

	private String type;

	private long preference;

	@OneToOne(targetEntity = Room.class)
	private Room room;

}
