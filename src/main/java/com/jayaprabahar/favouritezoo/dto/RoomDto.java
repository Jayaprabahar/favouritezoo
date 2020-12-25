package com.jayaprabahar.favouritezoo.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : RoomDto.java </p>
 * <p> Description: Data Transfer object for Room. For Room object Requests  </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Data
@AllArgsConstructor
public class RoomDto {

	@NotNull(message = "Title field is missing")
	private String title;

	@NotNull(message = "Size field is missing")
	private Long size;
}
