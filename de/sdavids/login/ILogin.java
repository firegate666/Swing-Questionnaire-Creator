/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

import java.util.Date;

import de.sdavids.interfaces.INullable;

/**
 * Interface for a login.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-13
 */
public interface ILogin extends INullable, Cloneable {

	/* ----------- Mutators --------------------- */

	/**
	 * The login is considered &quot;logged in&quot; from
	 * now on util {@link #beLoggedOut beLoggedOut} is called.
	 * 
	 * @see	#beLoggedOut.
	 * @see	#getLoginTime.
	 */
	void beLoggedIn();

	/**
	 * The login is considered &quot;logged out&quot; from
	 * now on.
	 * 
	 * @see	#beLoggedIn.
	 */
	void beLoggedOut();

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the name of the account.
	 * 
	 * @return	the name.
	 */
	String getAccount();

	/**
	 * Answer the account's password.
	 * 
	 * @return	the password.
	 */
	String getPassword();

	/**
	 * Answer the login time.
	 * 
	 * @return	the date or <code>null</code> if the login is logged out.
	 * @see	#isLoggedIn.
	 */
	Date getLoginTime();

	/**
	 * Answer if the given string is the password.
	 *
	 * @return	<code>true</code> if <code>pwd</code> is
	 * 			the password.
	 */
	boolean isPassword(String pwd);

	/**
	 * Answer if the login is logged in.
	 *
	 * @return	<code>true</code> if it is logged in.
	 */
	boolean isLoggedIn();

	/**
	 * Answer if the login is logged out.
	 *
	 * @return	<code>true</code> if it is logged out.
	 */
	boolean isLoggedOut();

	/**
	 * Creates and returns a copy of this object.
	 *  
	 * @return	a clone of this instance.
	 * @throws	CloneNotSupportedException if this object cannot be cloned.
	 * @throws	OutOfMemoryError</code> if there is not enough memory.
	 * @see	java.lang.Cloneable.
	 */
	Object clone() throws CloneNotSupportedException;
}