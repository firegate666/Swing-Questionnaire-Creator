/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.exceptions;

/**
 * Thrown if <code>instance()</code> is called on not initialized singleton 
 * (via <code>init()</code>).
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.01	02-01-21
 */
public class SingletonException extends ChainedRuntimeException {

	/**
	 * Constructs a <code>InvalidEntryException</code> with <code>null</code>
	 * as its error message string and no cause.
	 */
	public SingletonException() {
		super();
	}

	/**
	 * Constructs a <code>InvalidEntryException</code> with no cause, saving a
	 * reference to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method.
	 *
	 * @param	message		the detail message.
	 */
	public SingletonException(String s) {
		super(s);
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
	public SingletonException(String message, Throwable cause) {
		super(message, cause);
	}
}