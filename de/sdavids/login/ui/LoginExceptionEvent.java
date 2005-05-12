/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.login.ui;

import java.util.EventObject;

import de.sdavids.exceptions.ConnectionException;
import de.sdavids.login.ILogin;
import de.sdavids.login.LoginFactory;

/**
 * An immutable <code>LoginExceptionEvent</code>.
 * 
 * @see LoginExceptionListener.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public class LoginExceptionEvent extends EventObject {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The login. */
	private ILogin fLogin;

	/**
	 * The <code>Exception</code> to be propagated.
	 */
	private ConnectionException fException;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create a new <code>LoginExceptionEvent</code>.
	 * 
	 * @param	<code>source</code> the source.
	 * @param	<code>login</code> the login.
	 * @param	<code>ex</code> the exception.
	
	 */
	public LoginExceptionEvent(
		Object source,
		ILogin login,
		ConnectionException ex) {
		super(source);

		fLogin = login;
		fException = ex;
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the login.
	 * 
	 * @return	a clone of the original login or the <code>NullLogin</code>
	 * 			if it cannot be cloned.
	 */
	public ILogin getLogin() {
		if (null == fLogin)
			return LoginFactory.getNullLogin();
		if (fLogin.isNull())
			return fLogin;

		try {
			return (ILogin) fLogin.clone();
		} catch (CloneNotSupportedException e) {
			return LoginFactory.getNullLogin();
		}
	}

	/**
	 * Answer the exception.
	 * 
	 * @return	a copy of the original exception.
	 */
	public ConnectionException getException() {
		//Exceptions are not Cloneable so do it the hard way
		Throwable t = fException.getCause();

		if (null == t)
			return new ConnectionException(new String(fException.getMessage()));

		t.fillInStackTrace();

		return new ConnectionException(
			new String(fException.getMessage()),
			new Throwable(t.getMessage()));
	}
}