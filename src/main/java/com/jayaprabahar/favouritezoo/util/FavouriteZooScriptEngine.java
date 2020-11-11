/**
 * 
 */
package com.jayaprabahar.favouritezoo.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Component;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : FavouriteZooScriptEngine.java </p>
 * <p> Description: TODO </p>
 * <p> Created: Nov 11, 2020 </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 *
 */
@Component
public class FavouriteZooScriptEngine {

	public boolean evaluateBooleanScripts(String script) {
		ScriptEngine nashornEngine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			return Boolean.valueOf(String.valueOf(nashornEngine.eval(script)));
		} catch (ScriptException e) {
		}
		return false;
	}

}
