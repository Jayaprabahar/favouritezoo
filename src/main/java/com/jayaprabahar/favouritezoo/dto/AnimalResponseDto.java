/**
 * 
 */
package com.jayaprabahar.favouritezoo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class AnimalResponseDto {

	private String title;

	private LocalDateTime added;

	private LocalDateTime located;
}
