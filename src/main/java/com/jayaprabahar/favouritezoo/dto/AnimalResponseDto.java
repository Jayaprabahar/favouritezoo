package com.jayaprabahar.favouritezoo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : AnimalResponseDto.java </p>
 * <p> Description: Data Transfer object for Animal response </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Value
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class AnimalResponseDto {

	String title;

	Instant added;

	Instant located;
}
