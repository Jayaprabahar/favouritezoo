package com.jayaprabahar.favouritezoo.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Component;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteZooScriptEngine.java </p>
 * <p> Description: ScriptEngine class that evaluates different string based mathematical representations combinations</p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Component
public class FavouriteZooScriptEngine {

	/**
	 * Loads the nashorn javascript engine and evaluate it.
	 * Performance of this component is not measured
	 * 
	 * @param script NashRoom script String
	 * @return boolean - TRUE if the evaluation is success
	 */
	public boolean evaluateBooleanScripts(String script) {
		ScriptEngine nashornEngine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			return Boolean.valueOf(String.valueOf(nashornEngine.eval(script)));
		} catch (ScriptException e) {
			// DO NOTHING
		}
		return false;
	}

}
