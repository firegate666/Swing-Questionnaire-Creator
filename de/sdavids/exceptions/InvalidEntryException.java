/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.exceptions;

/**
 * A <code>InvalidEntryException</code> is thrown if the value entered is
 * not valid.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.01	02-01-21
 */
public class InvalidEntryException extends ChainedException {

	/**
	 * Constructs a <code>InvalidEntryException</code> with <code>null</code>
	 * as its error message string and no cause.
	 */
	public InvalidEntryException() {
		super();
	}

	/**
	 * Constructs a <code>InvalidEntryException</code> with no cause, saving a
	 * reference to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method.
	 *
	 * @param	message		the detail message.
	 */
	public InvalidEntryException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>InvalidEntryException</code>, saving a reference
	 * to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method and the cause (The cause is the
	 * throwable that caused this throwable to get thrown.); retrieve with
	 * {@link #getCause getCause()}.
	 *
	 * @param	message		the detail message.
	 * @param	cause		the cause.
	 */
	public InvalidEntryException(String message, Throwable cause) {
		super(message, cause);
	}
}