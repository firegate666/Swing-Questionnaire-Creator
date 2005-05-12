/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
 
package de.sdavids.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import javax.swing.event.EventListenerList;

import de.sdavids.interfaces.INullable;

/**
 * An implementation of the <code>Action</code> interface.
 * <p>
 * Provides convenience methods for setting and getting the different action
 * properties.
 * <p>
 * Some ideas have been borrowed from the following article:
 * <a href="http://java.sun.com/products/jfc/tsc/articles/actions/index.html">
 * Using the Swing Action Architecture</a>
 * <p>
 * Article contains a similar class named <code>JLFAbstractAction.java</code>.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-18
 */
abstract public class ActionImpl extends AbstractAction implements INullable {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Class Attributes ------------- */
		
	/**
	 * The key used for storing the selection state of this action.
	 */
	public static final String SELECTED = "selected"; //non-I18N-NLS
	
	/**
	 * The key used for storing a large icon for the action,
	 * used for toolbar buttons.
	 */
	public static final String LARGE_ICON = "LargeIcon"; //non-I18N-NLS

	/* ----------- Instance Attributes ---------- */
	
	/**
	 * The listeners for <code>ActionEvent</code>s.
	 */
	private EventListenerList fListeners;

	/**
	 * Whether this action can be selected or not.
	 */
	private boolean fSelectable; //non-I18N-NLS

	//////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Create an <code>ActionImpl<code> with the specified name.
	 *
	 * @param	name	the name.
	 */
	protected ActionImpl(String name) {
		super(name);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the accelerator key.
	 *
	 * @param	key		the key.
	 */
	public void setAccelerator(String key) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(key));
	}

	/**
	 * Set the action command key.
	 * 
	 * @param	key		the key.
	 */
	public void setActionCommand(String key) {
		putValue(ACTION_COMMAND_KEY, key);
	}

	/**
	 * Set the long description.
	 * 
	 * @param	description	the description.
	 */
	public void setLongDescription(String description) {
		putValue(LONG_DESCRIPTION, description);
	}

	/**
	 * Set the mnemonic key.
	 * 
	 * @param	key		the key.
	 */
	public void setMnemonicKey(int key) {
		putValue(MNEMONIC_KEY, new Integer(key));
	}

	/**
	 * Set the name.
	 * 
	 * @param	name	the name.
	 */
	public void setName(String name) {
		putValue(NAME, name);
	}

	/**
	 * Set the short description.
	 * 
	 * @param	description	the description.
	 */
	public void setShortDescription(String description) {
		putValue(SHORT_DESCRIPTION, description);
	}

	/**
	 * Set the icon.
	 * 
	 * @param	icon the icon.
	 */
	public void setIcon(Icon icon) {
		putValue(SMALL_ICON, icon);
	}

	/**
	 * Set the large icon used for toolbar buttons.
	 * 
	 * @param	icon	the icon.
	 */
	public void setLargeIcon(Icon icon) {
		putValue(LARGE_ICON, icon);
	}

	/**
	 * Set the selection state.
	 * <p>
	 * <code>isSelected == true</code> reprepresents a selected action.
	 * 
	 * @param	isSelected the selection state.
	 */
	public void setSelected(boolean isSelected) {
		putValue(SELECTED, new Boolean(isSelected));
	}

	/**
	 * Make this action a selectable action.
	 * <p>
	 * <em>Note:</em> Once this action is selectable it cannot be made
	 * &quot;unselectable&quot;.
	 */
	public void beSelectable() {
		fSelectable = true;
	}
			
	/* ----------- Accessors -------------------- */
	
	/**
	 * Answer the action command key.
	 * 
	 * @return	action command key.
	 */
	public String getActionCommand(String key) {
		return (String) getValue(ACTION_COMMAND_KEY);
	}

	/**
	 * Answer the accelerator key.
	 *
	 * @return	the key.
	 */
	public KeyStroke getAccelerator() {
		return (KeyStroke) getValue(ACCELERATOR_KEY);
	}

	/**
	 * Answer the long description.
	 * 
	 * @return	the description.
	 */
	public String getLongDescription() {
		return (String) getValue(LONG_DESCRIPTION);
	}

	/**
	 * Answer the mnemonic key.
	 * <p>
	 * Call {@link #hasMnemonicKey hasMnemonicKey()} to be sure
	 * that this action has a key.
	 * 
	 * @return	the key or <code>Integer.MIN_VALUE</code> if this
	 * 			action has no mnemonic key.
	 */
	public int getMnemonicKey() {
		if (!hasMnemonicKey())
			return Integer.MIN_VALUE;
			
		return ((Integer) getValue(MNEMONIC_KEY)).intValue();
	}

	/**
	 * Answer the name.
	 * 
	 * @return	the name.
	 */
	public String getName() {
		return (String) getValue(NAME);
	}

	/**
	 * Answer the short description.
	 * 
	 * @return	the description.
	 */
	public String getShortDescription() {
		return (String) getValue(SHORT_DESCRIPTION);
	}

	/**
	 * Answer the icon.
	 * 
	 * @return	the icon.
	 */
	public Icon getIcon() {
		return (Icon) getValue(SMALL_ICON);
	}

	/**
	 * Answer the large icon.
	 * 
	 * @return	the icon.
	 */
	public Icon getLargeIcon() {
		return (Icon) getValue(LARGE_ICON);
	}

	/**
	 * Answer the selection state.
	 * 
	 * @return	<code>true</code> if the action is selected.
	 */
	public boolean isSelected() {
		return ((Boolean) getValue(SELECTED)).booleanValue();
	}

	/**
	 * Answer if this action is selectable.
	 *
	 * @return	<code>true</code> if it is selectable.
	 */
	public boolean isSelectable() {
		return fSelectable;
	}
	/**
	 * Answer if this action has a mnemonic key.
	 * 
	 * @return	<code>true</code> if it has a mnemonic key.
	 */
	public boolean hasMnemonicKey() {
		return (null != getValue(MNEMONIC_KEY));
	}
			
	/* ----------- Event handling --------------- */
	
	/**
	 * Add an <code>ActionListener</code> to this action.
	 * <p>
	 * Used to forward <code>ActionEvent</code>s to other actions.
	 * 
	 * @param l	the <code>ActionListener</code> to be added.
	 */
	public void addActionListener(ActionListener l) {
		if (null == l)
			return;
		if (null == fListeners)
			fListeners = new EventListenerList();

		fListeners.add(ActionListener.class, l);
	}
	
	/**
	 * Removes an <code>ActionListener</code> from this action.
	 *
	 * @param l	the <code>ActionListener</code> to be removed.
	 */
	public void removeActionListener(ActionListener l) {
		if ((null == l) || (null == fListeners))
			return;

		fListeners.remove(ActionListener.class, l);
	}

	/** 
	 * Forward an <code>ActionEvent</code> to registered listeners.
	 * <p>
	 * The event instance is created using the parameters passed into the
	 * fire method.
	 * <p>
	 * The forwarded event will have the the our own own
	 * <code>ACTION_COMMAND_KEY</code> instead of the original's.
	 * 
	 * @param	evt		the event to be forwarded.
	 * @see	javax.swing.event.EventListenerList.
	 * @see	javax.swing.Action#ACTION_COMMAND_KEY.
	 */
	public void actionPerformed(ActionEvent evt) {
		if (null == fListeners)
			return;

		Object[] listeners = fListeners.getListenerList();

		ActionEvent newEvt =
			new ActionEvent(
				evt.getSource(),
				evt.getID(),
				(String) getValue(ACTION_COMMAND_KEY),
				evt.getModifiers());

		for (int i = (listeners.length - 2); i >= 0; i -= 2) {
			((ActionListener) listeners[i + 1]).actionPerformed(newEvt);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////
	// IMPLEMENTS INULLABLE
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer that we are not the <em>Null-object</em>.
	 *
	 * @return	<code>false</code>.
	 */
	public boolean isNull() {
		return false;
	}	
}