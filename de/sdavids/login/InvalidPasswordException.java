/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

import de.sdavids.exceptions.InvalidEntryException;

/**
 * A <code>InvalidPasswordException</code> is thrown if the password entered is
 * not valid.
 * 
 * @author		Sebastian Davids
 * @version	1.01	02-01-21
 */
public class InvalidPasswordException extends InvalidEntryException {

	/////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a <code>InvalidPasswordException</code> with
	 * <code>null</code> as its error message string and no cause.
	 */
	public InvalidPasswordException() {
		super();
	}

	/**
	 * Constructs a <code>InvalidPasswordException</code> with no cause, saving
	 * a reference to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method.
	 *
	 * @param	message		the detail message.
	 */
	public InvalidPasswordException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>InvalidPasswordException</code>, saving a reference
	 * to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method and the cause (The cause is the
	 * throwable that caused this throwable to get thrown.); retrieve with
	 * {@link #getCause getCause()}.
	 *
	 * @param	message		the detail message.
	 * @param	cause		the cause.
	 */
	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}