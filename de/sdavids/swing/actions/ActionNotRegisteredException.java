/*
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. Alle Rechte vorbehalten.
 * ===========================================================================
 */

package de.sdavids.swing.actions;

import de.sdavids.exceptions.*;

/**
 * A <code>InvalidPasswordException</code> is thrown if a connection error with a
 * connection occurred.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-18
 */
public class ActionNotRegisteredException extends ChainedException {

	/**
	 * Constructs a <code>InvalidPasswordException</code> with <code>null</code>
	 * as its error message string.
	 */
	public ActionNotRegisteredException() {
		super();
	}

	/**
	 * Constructs a <code>InvalidPasswordException</code>, saving a reference
	 * to the error message string for later retrieval by the
	 * <code>getMessage</code> method.
	 *
	 * @param	<code>message</code> the detail message.
	 */
	public ActionNotRegisteredException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>InvalidPasswordException</code>, saving a reference
	 * to the error message string for later retrieval by the
	 * <code>getMessage</code> method.
	 *
	 * @param	<code>message</code> the detail message.
	 * @param	<code>cause</code> the cause.
	 */
	public ActionNotRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}
}