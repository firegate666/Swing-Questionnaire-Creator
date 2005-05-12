/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
 
package de.sdavids.login.ui;

import java.util.EventObject;

import de.sdavids.login.ILogin;
import de.sdavids.login.LoginFactory;

/**
 * An immutable <code>LoginEvent</code>.
 * 
 * @see LoginListener.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public class LoginEvent extends EventObject {
	
	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Instance Attributes ---------- */

	/**
	 * If <code>true</code> we want to log in; if <code>false</code> we want
	 * to log out.
	 */	
	private boolean fIsLogin;
	
	/** The login. */
	private ILogin fLogin;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Create a new <code>LoginEvent</code>.
	 * 
	 * @param	source		the source.
	 * @param	isLogin		<code>true</code> if we want to log in;
	 * 						<code>false</code> if we want to log out.
	 * @param	login		the login.
	 */
	public LoginEvent(Object source, boolean isLogin, ILogin login) {
		super(source);
		
		fIsLogin = isLogin;
		fLogin = login;
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////
	
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
	 * Answer if we want to log in.
	 *
	 * @return	<code>true</code> if it is login.
	 */	
	public boolean isLogin() {
		return fIsLogin;
	}

	/**
	 * Answer if we want to log out.
	 *
	 * @return	<code>true</code> if it is logout.
	 */	
	public boolean isLogout() {
		return !isLogin();
	}
}	
