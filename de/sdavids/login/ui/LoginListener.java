/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.login.ui;

import java.util.EventListener;

/**
 * An contract between a <code>LoginEvent</code> and a listener class.
 * 
 * @see		LoginEvent.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public interface LoginListener extends EventListener {

	/**
	 * Called when the login's status changes.
	 * 
	 * @param	<code>evt</code> the event.
	 */
	public void loginChange(LoginEvent evt);
}