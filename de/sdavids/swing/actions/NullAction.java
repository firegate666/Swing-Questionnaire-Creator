/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
 
package de.sdavids.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;

import de.sdavids.exceptions.SingletonException;

/**
 * <code>Action</code> <em>Null-object</em>.
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
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.02	02-01-21
 */
class NullAction extends ActionImpl {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ------------- */

	/** The singleton. */
	private static NullAction INSTANCE;

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
			INSTANCE = new NullAction();
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
	public static NullAction instance() throws SingletonException {
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
	protected NullAction() {
		super(null);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Common interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object is the
	 * <code>NullAction</code>.
	 *
	 * @param     obj		the <code>Object</code> to compare with.
	 * @return    <code>true</code> if it is the <code>NullAction</code>.
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

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * No change in object regardless of parameter.
	 */
	public void setSelected(boolean isSelected) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setAccelerator(String key) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setActionCommand(String key) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setLongDescription(String description) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setMnemonicKey(int key) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setName(String name) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setShortDescription(String description) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setIcon(Icon icon) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setLargeIcon(Icon icon) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void putValue(String key, Object newValue) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setEnabled(boolean enabled) {}

	/* ----------- Accessors --------------------- */

	/**
	 * Always answer <code>false</code>.
	 * 
	 * @return	<code>false</code>.
	 */
	public boolean isSelected() {
		return false;
	}

	/**
	 * Always answer <code>0</code>.
	 * 
	 * @return	<code>0</code>.
	 */
	public int getMnemonicKey() {
		return 0;
	}

    /**
     * Throws a <code>CloneNotSupportedException</code>.
     *  
     * @throws	CloneNotSupportedException is always thrown.
     * @see	java.lang.Cloneable.
     */ 	
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/* ----------- Event handling --------------- */

	/**
	 * No change in object regardless of parameter.
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void addActionListener(ActionListener l) {}

	/**
	 * Event is not handled nor forwarded.
	 */		
	public void actionPerformed(ActionEvent evt) {}
	
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