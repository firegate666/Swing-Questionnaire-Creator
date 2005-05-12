/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

/**
 * Use only for JUnit-testing!
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-13
 */
public class JUnitBridge {

	//////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Use only for JUnit-testing!
	 */
	public static LoginImpl newLoginImpl(String account, String password)
		throws InvalidAccountException, InvalidPasswordException {

		return new LoginImpl(account, password);
	}
}