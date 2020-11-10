/**
 * 
 */
package com.jayaprabahar.favouritezoo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p> Project : mt940FileValidator </p>
 * <p> Title : GenericResponse.java </p>
 * <p> Description: </p>
 * <p> Created: Jul 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Data
@Builder
@AllArgsConstructor
public class GenericResponse {

	@Builder.Default
	LocalDateTime timestamp = LocalDateTime.now();

	@Builder.Default
	int status = HttpStatus.BAD_REQUEST.value();

	String error;

	@Builder.Default
	String message = "Invalid input";

}
