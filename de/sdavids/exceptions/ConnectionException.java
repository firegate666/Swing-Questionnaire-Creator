/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.exceptions;

/**
 * A <code>ConnectionException</code> is thrown if a connection error with a
 * connection occurred.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.01	02-01-21
 */
public class ConnectionException extends ChainedException {

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a <code>ConnectionException</code> with <code>null</code>
	 * as its error message string and no cause.
	 */
	public ConnectionException() {
		super();
	}

	/**
	 * Constructs a <code>ConnectionException</code> with no cause, saving a
	 * reference to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method.
	 *
	 * @param	message		the detail message.
	 */
	public ConnectionException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>ConnectionException</code>, saving a reference
	 * to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method and the cause (The cause is the
	 * throwable that caused this throwable to get thrown.); retrieve with
	 * {@link #getCause getCause()}.
	 *
	 * @param	message		the detail message.
	 * @param	cause		the cause.
	 */
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}