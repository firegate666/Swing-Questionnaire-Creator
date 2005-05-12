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
 * <code>LoginListener</code> interface.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public class LoginEventMulticaster
	extends AWTEventMulticaster
	implements LoginListener {

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor to support multicast events.
	 * 
	 * @param	<code>aListener</code> a listener
	 * @param	<code>anotherListener</code> another listener
	 */
	protected LoginEventMulticaster(
		EventListener aListener,
		EventListener anotherListener) {

		super(aListener, anotherListener);
	}

	//////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @param	aListener		a listener
	 * @param	anotherListener	another listener
	 * @return	a listener
	 */
	public static LoginListener add(
		LoginListener aListener,
		LoginListener anotherListener) {

		return (LoginListener) addInternal(aListener, anotherListener);
	}

	/**
	 * Remove listener to support multicast events and
	 * returns the resulting multicast listener.
	 * 
	 * @param	aListener		a listener
	 * @param	oldListener		the old listener
	 * @return	a listener
	 */
	public static LoginListener remove(
		LoginListener aListener,
		LoginListener oldListener) {

		if ((null == aListener) || (aListener == oldListener))
			return null;

		if (aListener instanceof LoginEventMulticaster)
			return (LoginListener) ((LoginEventMulticaster) aListener).remove(
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

		return new LoginEventMulticaster(aListener, anotherListener);
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
	protected EventListener remove(LoginListener oldListener) {
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
	// IMPLEMENT ILOGINEVENT
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators -------------------- */

	/**
	 * Called when the login's status changes.
	 * 
	 * @param	evt			the event.
	 */
	public void loginChange(LoginEvent event) {
		((LoginListener) a).loginChange(event);
		((LoginListener) b).loginChange(event);
	}
}