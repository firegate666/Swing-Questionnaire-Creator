package de.sdavids.util;

import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.KeyStroke;

public class KeyCodeUtils {

	protected static String KEY_CODE = "keyCode ";
	protected static String KEY_CHAR = "keyChar ";
	protected static String P = "-P";
	protected static String R = "-R";
	
	//strips "keyCode" and "-P" or "-R" and inserts a "+" between the modifiers and the key
	public static String toStandardRepresentation(KeyStroke key) {
		String result = key.toString();
		
		result = StringUtils.replace(result, KEY_CODE , "");
		result = StringUtils.replace(result, KEY_CHAR , "");
		result = StringUtils.replace(result, P, "");
		result = StringUtils.replace(result, R, "");

		String modifiers = KeyEvent.getKeyModifiersText(key.getModifiers());
		
		result = StringUtils.replace(result, modifiers, (modifiers + "+"));
						
		return result;
	}
}