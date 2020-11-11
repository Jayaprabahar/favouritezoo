/**
 * 
 */
package com.jayaprabahar.favouritezoo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalDto.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 10, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Data
@JsonInclude(Include.NON_EMPTY)
public class AnimalDto {

	@NotNull(message = "Title field is missing")
	private String title;
	
	@NotBlank(message = "Type field is missing")
	private String type;
	
	@Positive(message = "Preference field is missing")
	private long preference;

}
