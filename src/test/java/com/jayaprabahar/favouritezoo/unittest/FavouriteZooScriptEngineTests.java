/**
 * 
 */
package com.jayaprabahar.favouritezoo.unittest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.favouritezoo.util.FavouriteZooScriptEngine;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteZooScriptEngineTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 12, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@SpringBootTest
class FavouriteZooScriptEngineTests {

	@Autowired
	FavouriteZooScriptEngine scriptEngine;

	/**
	 * Check all mathematical conditions such as <=, >=, == with Nashroom Script Engine
	 * 
	 */
	@Test
	@DisplayName("Check all mathematical conditions such as <=, >=, == for animal room prefernece")
	void testScriptEngineEvaluate() {
		long roomSize = 30;
		String preferenceType = "<=";
		long preference = 25;

		assertFalse(scriptEngine.evaluateBooleanScripts(roomSize + preferenceType + preference));
		preferenceType = ">";

		assertTrue(scriptEngine.evaluateBooleanScripts(roomSize + preferenceType + preference));

		roomSize = 40;
		preferenceType = "==";
		preference = 40;

		assertTrue(scriptEngine.evaluateBooleanScripts(roomSize + preferenceType + preference));
	}

}
