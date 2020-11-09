/**
 * 
 */
package com.jayaprabahar.favouritezoo.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalDto.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 8, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Data
public class AnimalDto {

	private String title;
	private LocalDateTime located;
	private String type = "";
	private long preference;

}
