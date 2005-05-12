/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

import java.util.Date;

/**
 * Implementation of the <code>ILogin</code> interface.
 * 
 * @author		Sebastian Davids
 * @version	1.01	02-01-13
 */
class LoginImpl implements ILogin {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The account's name. */
	private String fAccount;

	/**
	 * The password.
	 */
	private String fPWd;

	/** The login time. */
	private Date fLoginTime;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Use only for the <code>NullLogin</code>.
	 */
	protected LoginImpl() {
		fPWd = "";
		fAccount = "";
		
		beLoggedOut();
	}

	/**
	 * Create a new login.
	 * <p>
	 * The login will be logged out initially.
	 * 
	 * @param	password	the password.
	 * @param	account		the name.
	 * @throws	InvalidAccountException		if <code>account</code>
	 * 			is <code>null</code> or an empty string.
	 * @throws	InvalidPasswordException	if <code>password</code>
	 * 			is <code>null</code>.
	 */
	public LoginImpl(String account, String pwd)
		throws InvalidAccountException, InvalidPasswordException {

		setAccount(account);
		setPassword(pwd);
		
		beLoggedOut();
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Common interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object's account and
	 * password are equivalent to the specified object's account and password.
	 *
	 * @param	obj		the <code>Object</code> to compare with.
	 * @return	<code>true</code> if the conditions above are met.
	 */
	public synchronized boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof ILogin))
			return false;

		ILogin other = (ILogin) obj;

		if (!getAccount().equals(other.getAccount()))
			return false;
		if (!getPassword().equals(other.getPassword()))
			return false;

		return true;
	}

	/**
	 * Generates a hash code for the receiver.
	 *
	 * This method is supported primarily for maps, such as those  provided in
	 * <code>java.util.Map</code>.
	 *
	 * @return	an integer hash code for the receiver.
	 * @see 	java.util.Map.
	 */
	public synchronized int hashCode() {
		return super.hashCode();
	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return	a string representation of the object.
	 */
	public synchronized String toString() {
		return getAccount() + " " + fLoginTime;
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Mutators --------------------- */

	/**
	 * Set the name of the account.
	 * 
	 * @param	account		the name.
	 * @throws	InvalidAccountException		if <code>account</code> is
	 * 			<code>null</code> or an empty string.
	 */
	protected void setAccount(String account) throws InvalidAccountException {
		if (null == account)
			throw new InvalidAccountException("account null");
		if ("".equals(account))
			throw new InvalidAccountException("account empty string");

		fAccount = account;
	}

	/**
	 * Set the account's password.
	 * 
	 * @param	password the password.
	 * @throws	InvalidPasswordException	if <code>password</code> is
	 * 			<code>null</code>.
	 */
	protected void setPassword(String password) throws InvalidPasswordException {
		if (null == password)
			throw new InvalidPasswordException("password null");

		fPWd = password;
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ILOGIN
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * The login is considered &quot;logged in&quot; from now on util
	 * {@link #beLoggedOut beLoggedOut()} is called.
	 * 
	 * @see	#beLoggedOut.
	 * @see	#getLoginTime.
	 */
	public synchronized void beLoggedIn() {
		fLoginTime = new Date();
	}

	/**
	 * The login is considered &quot;logged out&quot; from now on.
	 * 
	 * @see	#beLoggedIn.
	 */
	public synchronized void beLoggedOut() {
		fLoginTime = null;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the name of the account.
	 * <p>
	 * The return value is a copy of the account name.
	 * 
	 * @return	the name.
	 */
	public String getAccount() {
		return new String(fAccount);
	}

	/**
	 * Answer the account' password.
	 * <p>
	 * The return value is a copy of the password.
	 * 
	 * @return	the password.
	 */
	public String getPassword() {
		return new String(fPWd);
	}

	/**
	 * Answer the log-in-time.
	 *
	 * The return value is a copy of the password.
	 * 
	 * @return	the date or <code>null</code> if the login is logged out.
	 * @see	#isLoggedIn.
	 */
	public synchronized Date getLoginTime() {
		//should return a NullDate
		return (null == fLoginTime) ? null : new Date(fLoginTime.getTime());
	}

	/**
	 * Answer if the given string is the password.
	 *
	 * @return	<code>true</code> if <code>pwd</code> is the password.
	 */
	public boolean isPassword(String pwd) {
		return fPWd.equals(pwd);
	}

	/**
	 * Answer if the login is logged in.
	 *
	 * @return	<code>true</code> if it is logged in.
	 */
	public synchronized boolean isLoggedIn() {
		return !isLoggedOut();
	}

	/**
	 * Answer if the login is logged out.
	 *
	 * @return	<code>true</code> if it is logged out.
	 */
	public synchronized boolean isLoggedOut() {
		return (null == fLoginTime);
	}

    /**
     * Creates and returns a copy of this object.
     *  
     * @return	a clone of this instance.
     * @throws	OutOfMemoryError		if there is not enough memory.
     * @see	java.lang.Cloneable.
     */ 	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();	
	}
	
	//////////////////////////////////////////////////////////////////////////
	// IMPLEMENTS INULLABLE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer that we are a not the <em>Null-object</em>.
	 *
	 * @return	<code>false</code>.
	 */
	public boolean isNull() {
		return false;
	}
}