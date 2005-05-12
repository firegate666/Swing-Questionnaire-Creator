/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * A base class for <code>Exception</code>-chains.
 * <em>Note:</em>
 * <p>
 * JDK 1.4 has extended the <code>Throwable</code> class to provide all the
 * features of this base class shown here. If you are developing your
 * application for JDK 1.4 or later, you can simply use the exception-chaining
 * features provided by <code>Throwable</code>. If you are writing code that
 * must run on JDK 1.2 or 1.3 but will eventually migrate to 1.4, you can use
 * this class now and then simply change your exception classes from extending
 * <code>ChainedException</code> to extending <code>Exception</code> when you
 * migrate.
 * <p>
 * Source:
 * <a href="http://developer.java.sun.com/developer/technicalArticles/Programming/exceptions2/">Exceptional practices, Part 2</a>
 * 
 * @author		Brian Goetz.
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.01	02-01-21
 */
public class ChainedException extends Exception {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */
		
	/** The throwable that caused this throwable to get thrown */
	private Throwable fCause;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a <code>ChainedException</code> with <code>null</code>
	 * as its error message string and no cause.
	 */
	public ChainedException() {
		super();
	}

	/**
	 * Constructs a <code>ConnectionException</code> with no cause, saving a
	 * reference to the error message string for later retrieval by the
	 * {@link #getMessage getMessage()} method.
	 *
	 * @param	message		the detail message.
	 */
	public ChainedException(String message) {
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
	public ChainedException(String message, Throwable cause) {
		super(message);
		
		initCause(cause);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Accessors -------------------- */
	
	/**
	 * Answer the cause of this throwable or <code>null</code> if the cause
	 * is nonexistent or unknown. (The cause is the throwable that caused this
	 * throwable to get thrown.) 
	 *
	 * @return	the cause of this throwable or <code>null</code> if the cause
	 * 			is nonexistent or unknown.
	 */	
	public Throwable getCause() {
		return fCause;
	}

    /**
     * Prints this <code>ChainedException</code> and its backtrace to the 
     * standard error stream.
     */
	public void printStackTrace() {
		super.printStackTrace();

		Throwable cause = getCause();

		if (null == cause)
			return;

		System.err.println(" Caused by:");

		cause.printStackTrace();
	}

    /**
     * Prints this <code>ChainedException</code> and its backtrace to the 
     * specified print stream.
     *
     * @param ps		<code>PrintStream</code> to use for output.
     */
	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);

		Throwable cause = getCause();

		if (null == cause)
			return;

		ps.println(" Caused by:");

		cause.printStackTrace(ps);
	}

    /**
     * Prints this <code>ChainedException</code> and its backtrace to the
     * specified print writer.
     *
     * @param pw		<code>PrintWriter</code> to use for output.
     */
	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);

		Throwable cause = getCause();

		if (null == cause)
			return;

		pw.println(" Caused by:");

		cause.printStackTrace(pw);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Mutators --------------------- */
	
    /**
     * This method can be called at most once. It is generally called from 
     * within the constructor, or immediately after creating the throwable.
     * If this throwable was created with {@link #Throwable(Throwable)
     * Throwable(Throwable)} or {@link #Throwable(String, Throwable)
     * Throwable(String, Throwable)}, this method cannot be called even once. 
     *
     * @param cause		the cause (which is saved for later retrieval by
     * 						the {@link #getCause getCause()} method). (A 	
     * 						<code>null</code> value is permitted, and indicates
     * 						that the cause is nonexistent or unknown.) 
     * @throws	IllegalArgumentException	if cause is this throwable. (A
     * 						throwable cannot be its own cause.) 
     * @throws	IllegalStateException		if this throwable was created with
     * 						{@link #Throwable(Throwable) Throwable(Throwable)}
     * 						{@link #Throwable(String, Throwable)
     * 						Throwable(String, Throwable)}, or this method has
     * 						already been called on this throwable.
     */	
	public Throwable initCause(Throwable cause) {
		if (this == cause)
			throw new IllegalArgumentException(cause.toString());

		if (null != getCause())
			throw new IllegalStateException("cause already set");

		fCause = cause;
		return this;
	}	
}