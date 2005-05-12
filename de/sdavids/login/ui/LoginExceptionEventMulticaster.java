/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.login.ui;

import java.awt.AWTEventMulticaster;
import java.util.EventListener;

/**
 * This is the event multicaster class to support the
 * <code>LoginExceptionListener</code> interface.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @ version 1.00 02 - 01 - 13
 */
public class LoginExceptionEventMulticaster
	extends AWTEventMulticaster
	implements LoginExceptionListener {

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor to support multicast events.
	 * 
	 * @param	aListener		a listener
	 * @param	anotherListener	another listener
	 */
	protected LoginExceptionEventMulticaster(
		EventListener aListener,
		EventListener anotherListener) {

		super(aListener, anotherListener);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @param	aListener		a listener
	 * @param	anotherListener	another listener
	 * @return	a listener
	 */
	public static LoginExceptionListener add(
		LoginExceptionListener aListener,
		LoginExceptionListener anotherListener) {

		return (LoginExceptionListener) addInternal(aListener, anotherListener);
	}

	/**
	 * Remove listener to support multicast events and
	 * returns the resulting multicast listener.
	 * 
	 * @param	<code>aListener</code> a listener
	 * @param	<code>oldListener</code> the old listener
	 * @return	a listener
	 */
	public static LoginExceptionListener remove(
		LoginExceptionListener aListener,
		LoginExceptionListener oldListener) {

		if ((null == aListener) || (aListener == oldListener))
			return null;

		if (aListener instanceof LoginExceptionEventMulticaster)
			return (LoginExceptionListener)
				((LoginExceptionEventMulticaster) aListener).remove(
				oldListener);

		return aListener;
	}

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @param	aListener		a listener
	 * @param	anotherListener	another listener
	 * @return	a listener
	 */
	protected static EventListener addInternal(
		EventListener aListener,
		EventListener anotherListener) {

		if (null == aListener)
			return anotherListener;

		if (null == anotherListener)
			return aListener;

		return new LoginExceptionEventMulticaster(aListener, anotherListener);
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Remove listener to support multicast events and
	 * returns the resulting multicast listener.
	 * 
	 * @param	oldListener		the old listener
	 * @return	a listener
	 */
	protected EventListener remove(LoginExceptionListener oldListener) {
		if (oldListener == a)
			return b;

		if (oldListener == b)
			return a;

		EventListener a2 = removeInternal(a, oldListener);
		EventListener b2 = removeInternal(b, oldListener);

		if ((a2 == a) && (b2 == b))
			return this;

		return addInternal(a2, b2);
	}

	//////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ILOGINEXCEPTIONLISTENER
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators -------------------- */

	/**
	 * Called when an exception occurs while operating on a given login.
	 * 
	 * @param	evt		the event.
	 */
	public void propagateException(LoginExceptionEvent event) {
		((LoginExceptionListener) a).propagateException(event);
		((LoginExceptionListener) b).propagateException(event);
	}
}