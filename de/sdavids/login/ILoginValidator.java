/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

/**
 * Interface for evaluating passwords and account names.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-13
 */
public interface ILoginValidator {

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the given string is a valid account name.
	 *
	 * @return	<code>true</code> if it is valid.
	 */
	boolean isValidAccount(String account);

	/**
	 * Answer the given string is a valid password.
	 *
	 * @return	<code>true</code> if it is valid.
	 */
	boolean isValidPassword(String password);

	/**
	 * Answer the given string is an invalid account name.
	 *
	 * @return	<code>true</code> if it is invalid.
	 */
	boolean isNotValidAccount(String account);

	/**
	 * Answer the given string is an invalid password.
	 *
	 * @return	<code>true</code> if it is invalid.
	 */
	boolean isNotValidPassword(String password);
}