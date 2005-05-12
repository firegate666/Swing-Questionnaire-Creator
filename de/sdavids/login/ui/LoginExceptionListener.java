/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.login.ui;

import java.util.EventListener;

/**
 * An contract between a <code>LoginExceptionEvent</code> and a listener class.
 * 
 * @see 		LoginExceptionEvent.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public interface LoginExceptionListener extends EventListener {

	/**
	 * Called when an exception occurs while operating on a given login.
	 * 
	 * @param	<code>evt</code> the event.
	 */
	public void propagateException(LoginExceptionEvent evt);
}