/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.login;

import de.sdavids.exceptions.SingletonException;

/**
 * Factory for <code>ILogin</code>s.
 * <p>
 * Also provides a default implementation of the <code>ILoginValidator</code>
 * interface.
 * <p>
 * Utilizes the <code>Factory Method</code> and <code>Singleton</code> patterns.
 * <p>
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 * 
 * @author		Sebastian Davids
 * @version	1.01	02-01-13
 */
public class LoginFactory {

	//////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Default implementation of the <code>ILoginValidator</code> interface.
	 * <p>
	 * Has the following rule set:
	 * 
	 * <ul>
	 * 	<li><code>null != account</code></li>
	 * 	<li><code>!"".equals(account)</code></li>
	 * 	<li><code>null != password</code></li>
	 *  <li><code>!"".equals(password)</code></li>
	 * </ul>
	 * <p>
	 * Singleton.
	 */
	protected static class DefaultLoginValidator implements ILoginValidator {

		//////////////////////////////////////////////////////////////////////
		// ATTRIBUTES
		//////////////////////////////////////////////////////////////////////

		/* ----------- Class Attributes ---------- */

		/** The singleton. */
		private static DefaultLoginValidator INSTANCE;

		//////////////////////////////////////////////////////////////////////
		// CLASS METHODS
		//////////////////////////////////////////////////////////////////////

		/* ----------- Singleton Initializer ----- */

		/**
		 * Initialize the singleton instance.
		 *
		 * This method that has to be called once, before one calls
		 * {@link #instance instance()}.
		 */
		public static synchronized void init() {
			if (null == INSTANCE)
				INSTANCE = new DefaultLoginValidator();
		}

		/* ----------- Singleton Accessor -------- */

		/**
		 * Returns a reference to the singleton instance.
		 *
		 * @return	the reference of the singleton instance.
		 * @throws	<code>SingletonException<code>	if 
		 * 			{@link #instance instance()} is called on a not
		 * 			initialized singleton (via {@link #init init()}).
		 * @see	#init
		 */
		public static DefaultLoginValidator instance() throws SingletonException {
			if (null == INSTANCE)
				throw new SingletonException();

			return INSTANCE;
		}

		//////////////////////////////////////////////////////////////////////
		// IMPLEMENTS ILOGINVALIDATOR
		//////////////////////////////////////////////////////////////////////

		/* ----------- Accessors -------------------- */

		/**
		 * Answer the given string is a valid account name.
		 *
		 * @return	<code>true</code> if it is valid.
		 */
		public boolean isValidAccount(String account) {
			return !isNotValidAccount(account);
		}

		/**
		 * Answer the given string is a valid password.
		 *
		 * @return	<code>true</code> if it is valid.
		 */
		public boolean isValidPassword(String password) {
			return !isNotValidPassword(password);
		}

		/**
		 * Answer the given string is an invalid account name.
		 *
		 * @return	<code>true</code> if it is invalid.
		 */
		public boolean isNotValidAccount(String account) {
			return ((null == account) || ("".equals(account)));
		}

		/**
		 * Answer the given string is an invalid password.
		 *
		 * @return	<code>true</code> if it is invalid.
		 */
		public boolean isNotValidPassword(String password) {
			return ((null == password) || ("".equals(password)));
		}
	}
	
	//////////////////////////////////////////////////////////////////////////
	// INITIALIZATION
	//////////////////////////////////////////////////////////////////////////
	
	static {
		/* ----------- Singletons ------------------- */		
		
		NullLogin.init();
		DefaultLoginValidator.init();
	}
	
	//////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Factory Methods -------------- */

	/**
	 * Create a new <code>ILogin</code> with the given account and password.
	 * <p>
	 * The default validator is used to validate the account and password.
	 * <p>
	 * Factory method.
	 * 
	 * @param	account		the account.
	 * @param	password	the password. 
	 * @throws InvalidAccountException	if the account does not conform to
	 * 			the default validator's rule set.
	 * @throws InvalidPasswordException	if the password does not conform to
	 * 			the default validator's rule set.
	 * @see	#getDefaultValidator.
	 * 
	 */
	public static ILogin create(String account, String password)
		throws InvalidAccountException, InvalidPasswordException {

		return create(account, password, getDefaultValidator());
	}

	/**
	 * Create a new <code>ILogin</code> with the given account, password and
	 * validator.
	 * <p>
	 * Factory method.
	 * 
	 * @param	account		the account.
	 * @param	password	the password. 
	 * @param	validator	the validator. 
	 * @throws InvalidAccountException	if the account does not conform to
	 * 			the given <code>ILoginValidator</code> rule set.
	 * @throws InvalidPasswordException	if the password does not conform to
	 * 			the given <code>ILoginValidator</code> rule set.
	 */
	public static ILogin create(
		String account,
		String password,
		ILoginValidator validator)
		throws InvalidAccountException, InvalidPasswordException {

		if (validator.isNotValidAccount(account))
			throw new InvalidAccountException(account);
		if (validator.isNotValidPassword(password))
			throw new InvalidPasswordException(password);

		return new LoginImpl(account, password);
	}

	/**
	 * Answer the default validator.
	 * 
	 * @return	the validator.
	 * @see	DefaultLoginValidator
	 */
	public static ILoginValidator getDefaultValidator() {
		return DefaultLoginValidator.instance();
	}

	/* ----------- Null-object accessor --------- */

	/**
	 * Answer the <code>NullLogin</code>.
	 * 
	 * @see	de.sdavids.login.NullLogin.
	 */
	public static ILogin getNullLogin() {
		return NullLogin.instance();
	}
}