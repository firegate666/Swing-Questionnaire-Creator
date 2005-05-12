/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

import de.sdavids.exceptions.SingletonException;

/**
 * <code>ILogin</code> <em>Null-object</em>.
 * 
 * Singleton.
 *
 * Call {@link #init init()} before first use of singleton to initilize it.
 *
 * Utilizes the <code>Singleton</code> pattern.
 * 
 * Reference:<br>
 * <dl>
 * <dt>Singleton<dt>
 * <dd>Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.</dd>
 * <dt>Introduce Null Object<dt>
 * <dd><a href="http://www.refactoring.com/catalog/introduceNullObject.html">Page on www.refactoring.com</a></dd>
 * </dl>
 * 
 * @author		Sebastian Davids
 * @version	1.01	02-01-21
 */
class NullLogin extends LoginImpl {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Class Attributes ------------- */

	/** The singleton. */
	private static NullLogin INSTANCE;

	//////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Singleton Initializer -------- */

	/**
	 * Initialize the singleton instance.
	 *
	 * This method that has to be called once, before one calls
	 * {@link #instance instance()}.
	 */
	public static synchronized void init() {
		if (null == INSTANCE)
			INSTANCE = new NullLogin();
	}

	/* ----------- Singleton Accessor ----------- */

	/**
	 * Returns a reference to the singleton instance.
	 *
	 * @return	the reference of the singleton instance.
	 * @throws	<code>SingletonException<code>	if 
	 * 			{@link #instance instance()} is called on a not
	 * 			initialized singleton (via {@link #init init()}).
	 * @see	#init
	 */
	public static NullLogin instance() throws SingletonException {
		if (null == INSTANCE)
			throw new SingletonException();

		return INSTANCE;
	}

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////
	
	/** 
	 * Prevent instantiantion.
	 */
	protected NullLogin() {
		super();
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Common interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object is the
	 * <code>NullLogin</code>.
	 *
	 * @param     obj		the <code>Object</code> to compare with.
	 * @return    <code>true</code> if it is the <code>NullLogin</code>.
	 */
	public synchronized boolean equals(Object obj) {
		return (this == obj);
	}

	/**
	 * Answer an empty string.
	 *
	 * @return	an empty string.
	 */
	public synchronized String toString() {
		return "";
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ILOGIN
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * No change in object regardless of parameter.
	 */
	public void beLoggedIn() {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void beLoggedOut() {}

	/* ----------- Accessors -------------------- */

    /**
     * Throws a <code>CloneNotSupportedException</code>.
     *  
     * @throws	CloneNotSupportedException is always thrown.
     * @see	java.lang.Cloneable.
     */ 	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}	

	//////////////////////////////////////////////////////////////////////////
	// IMPLEMENTS INULLABLE
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer that we are the <em>Null-object</em>.
	 *
	 * @return	<code>true</code>.
	 */
	public boolean isNull() {
		return true;
	}
}