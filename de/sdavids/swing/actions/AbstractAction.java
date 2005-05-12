/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
 
package de.sdavids.swing.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import de.sdavids.interfaces.IApplication;
import de.sdavids.swing.SwingCreator;

/**
 * An abstract action keeping a reference to the application.
 * <p>
 * This class has some convenience methods to call other actions from
 * this action.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.31	02-01-21
 */
abstract public class AbstractAction extends ActionImpl {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** A reference to the application. */
	private IApplication fApp;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>AbstractAction<code> object with the specified
	 * name and a reference to the application.
	 * <p>
	 * This action will be initialized with the values in the applications
	 * configuration.
	 *
	 * @param	name	the name.
	 * @param	app		a reference to the application.
	 * @throws	IllegalArgumentException	if <code>null == app</code>,
	 * 			<code>null == app.getConfig()</code> or if
	 * 			<code>null == app.getFrame()</code>.
	 * @see	de.sdavids.swing.SwingCreator#initAction
	 */
	protected AbstractAction(String name, IApplication app)
		throws IllegalArgumentException {

		super(name);

		setApp(app);

		SwingCreator.initAction(this, getApp().getConfig());
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 * @throws	IllegalArgumentException	if <code>null == app</code>,
	 * 			<code>null == app.getConfig()</code> or if
	 * 			<code>null == app.getFrame()</code>.
	 */
	protected void setApp(IApplication app) throws IllegalArgumentException {
		if (null == app)
			throw new IllegalArgumentException("app null"); //non-I18N-NLS
		if (null == app.getConfig())
			throw new IllegalArgumentException("app.getConfig() null"); //non-I18N-NLS
		if (null == app.getFrame())
			throw new IllegalArgumentException("app.getFrame() null"); //non-I18N-NLS

		fApp = app;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the reference to the application.
	 *
	 * @return	the reference.
	 */
	protected IApplication getApp() {
		return fApp;
	}

	/**
	 * Answer the action repository.
	 *
	 * @return	the repository.
	 */
	protected ActionRepository getActionRepository() {
		return getApp().getActionRepository();
	}
		
	/**
	 * Answer the application's <code>Frame</code>.
	 *
	 * @return	the frame.
	 */
	protected Frame getFrame() {
		return getApp().getFrame();
	}

	/**
	 * Answer the configuration.
	 *
	 * @return	the configuration.
	 */
	protected ResourceBundle getConfig() {
		return getApp().getConfig();
	}

	/**
	 * Answer the string resource with the given key.
	 *
	 * @param	key		the resource's key.
	 * @return	the string.
	 */
	protected String getConfig(String key) {
		return getApp().getConfig(key);
	}

	//////////////////////////////////////////////////////////////////////////
	// INTER-ACTION COMMUNICATION
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Enable the action with the given name.
	 *
	 * @param	name the name of the action.
	 */
	protected void enableAction(String name) {
		getActionRepository().enableAction(name);
	}

	/**
	 * Disable the action with the given name.
	 *
	 * @param	name the name of the action.
	 */
	protected void disableAction(String name) {
		getActionRepository().disableAction(name);
	}
	
	/**
	 * Select the action with the given name.
	 *
	 * @param	name the name of the action.
	 */
	protected void selectAction(String name) {
		getActionRepository().selectAction(name);
	}

	/**
	 * Deselect the action with the given name.
	 *
	 * @param	name the name of the action.
	 */
	protected void deselectAction(String name) {
		getActionRepository().deselectAction(name);
	}
	
	/* ----------- Accessors -------------------- */
		
	/**
	 * Answer if the action with the given name is enabled.
	 *
	 * @param	name the name of the action.
	 * @return	<code>true</code> if it is enabled.
	 */
	protected boolean isActionEnabled(String name) {
		return getActionRepository().isActionEnabled(name);
	}
	
	/**
	 * Answer if the action with the given name is selected.
	 *
	 * @param	name the name of the action.
	 * @return	<code>true</code> if it is selected.
	 */
	protected boolean isActionSelected(String name) {
		return getActionRepository().isActionSelected(name);
	}		

	/* ----------- Event handling --------------- */	
	
	/**
	 * Looks up the action with the given name and calls 
	 * {@link #actionPerformed(ActionEvent)
	 * actionPerformed(ActionEvent)} on the looked up action
	 * with the the given event.
	 * <p>
	 * Nothing happens if there's no action with the given name.
	 * 
	 * @param	name		the name of the action.
	 * @param	event		the action event.
	 * @throws	IllegalArgumentException	if <code>evt</code>
	 * 			is <code>null</code>.
	 */
	protected void performAction(String name, ActionEvent evt) {
		getActionRepository().performAction(name, evt);
	}

	/**
	 * Looks up the action with the given name, creates a new
	 * <code>ActionEvent</code> with the given information and calls
	 * {@link #actionPerformed(ActionEvent)
	 * actionPerformed(ActionEvent)} on the looked up action
	 * with the newly created event.
	 * <p>
	 * Nothing happens if there's no action with the given name.
	 * 
	 * @param	name		the name of the action.
	 * @param	source		the object that originated the event.
	 * @param	id			an integer that identifies the event.
	 * @param	command		a string that may specify a command (possibly one
	 * 						of several) associated with the event.
	 */
	protected void performAction(
		String name,
		Object source,
		int id,
		String command) {

		getActionRepository().performAction(name, source, id, command);
	}

	/**
	 * Looks up the action with the given name, creates a new
	 * <code>ActionEvent</code> with the given information and calls
	 * {@link #actionPerformed(ActionEvent)
	 * actionPerformed(ActionEvent)} on the looked up action
	 * with the newly created event.
	 * <p>
	 * Nothing happens if there's no action with the given name.
	 * 
	 * @param	name		the name of the action.
	 * @param	source		the object that originated the event.
	 * @param	id			an integer that identifies the event.
	 * @param	command		a string that may specify a command (possibly one
	 * 						of several) associated with the event.
	 * @param	modifiers	the modifier keys held down during this action.
	 */
	protected void performAction(
		String name,
		Object source,
		int id,
		String command,
		int modifiers) {

		getActionRepository().performAction(name, source, id, command, modifiers);
	}
}