/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing.actions;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import de.sdavids.interfaces.IApplication;
import de.sdavids.swing.SwingCreator;
import de.sdavids.util.ReflectionUtils;

/**
 * A repository for <code>AbstractAction</code>s.
 * <p>
 * This repository supports both lazy and eager initialization of actions.
 * <p>
 * Use {@link #registerAction(String, String) registerAction(String, String)}
 * for lazy intialization.
 * <p>
 * Use {@link #registerAction(ActionImpl) registerAction(ActionImpl)}
 * for eager intialization.
 * <p>
 * The underlying <code>Map</code>s are not thread-safe.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.40	02-01-21
 */
public class ActionRepository {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** A reference to the application. */
	private IApplication fApp;

	/** The actions already instantiated in this repository. */
	private Map fActions;

	/** The actions to be lazily instantiated in this repository. */
	private Map fLazyActions;

	//////////////////////////////////////////////////////////////////////////
	// INITIALIZATION
	//////////////////////////////////////////////////////////////////////////

	static {
		/* ----------- Singletons ------------------- */

		NullAction.init();
	}

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructs a new <code>ActionRepository</code>.
	 * 
	 * @param	app	the application this repostitory belongs to.
	 * @throws	IllegalArgumentException	if <code>null == app</code>,
	 * 			<code>null == app.getConfig()</code> or if
	 * 			<code>null == app.getFrame()</code>.
	 */
	public ActionRepository(IApplication app) throws IllegalArgumentException {
		setApp(app);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */
	
	/**
	 * Enable the action with the given name.
	 *
	 * @param	name the name of the action.
	 */	
	public synchronized void enableAction(String name) {
		lookup(name).setEnabled(true);
	}

	/**
	 * Disable the action with the given name.
	 *
	 * @param	name the name of the action.
	 */	
	public synchronized  void disableAction(String name) {
		lookup(name).setEnabled(false);
	}

	/**
	 * Selects the action with the given name.
	 *
	 * @param	name the name of the action.
	 */	
	public synchronized void selectAction(String name) {
		lookup(name).setSelected(true);
	}

	/**
	 * Deselects the action with the given name.
	 *
	 * @param	name the name of the action.
	 */	
	public synchronized void deselectAction(String name) {
		lookup(name).setSelected(false);
	}
	
	/* ----------- Accessors -------------------- */
	
	/**
	 * Answer the action with the given name.
	 *
	 * @param	name		the action's name.
	 * @return	the action with the given name or the <code>NullAction</code>
	 * 			if no action with the given name is registered in this
	 * 			repository or if the action cannot be lazily instantiated.
	 */
	public synchronized ActionImpl lookup(String name) {
		if (getActions().containsKey(name))
			return (ActionImpl) getActions().get(name);

		if (getLazyActions().containsKey(name))
			return getLazyInstance(name);

		return NullAction.instance();
	}
	
	/**
	 * Answer if the action with the given name is enabled.
	 *
	 * @param	name the name of the action.
	 * @return	<code>true</code> if it is enabled.
	 */	
	public synchronized boolean isActionEnabled(String name) {
		return lookup(name).isEnabled();
	}

	/**
	 * Answer if the action with the given name is selected.
	 *
	 * @param	name the name of the action.
	 * @return	<code>true</code> if it is enabled.
	 */	
	public synchronized boolean isActionSelected(String name) {
		return lookup(name).isSelected();
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
	public synchronized void performAction(String name, ActionEvent evt)
		throws IllegalArgumentException {

		if (null == evt)
			throw new IllegalArgumentException("evt null");

		lookup(name).actionPerformed(evt);
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
	public synchronized void performAction(
		String name,
		Object source,
		int id,
		String command) {

		performAction(name, source, id, command, 0);
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
	public synchronized void performAction(
		String name,
		Object source,
		int id,
		String command,
		int modifiers) {

		performAction(name, new ActionEvent(source, id, command, modifiers));
	}

	/**
	 * Register the given action with this application.
	 * <p>
	 * Use this method for eagerly instantiated actions.
	 * <p>
	 * The action can be later accessed through 
	 * {@link #performAction(String, Object, int, String)
	 * performAction(String, Object, int, String)} or 
	 * {@link #performAction(String, ActionEvent)
	 * performAction(String, ActionEvent)}
	 * <p>
	 * Just use <code>action.getName()</code> as the <code>name</code>
	 * parameter of those methods.
	 * <p>
	 * The <code>NullAction</code> will not be registered.
	 * 
	 * @param	action		the action.
	 * @throws	IllegalArgumentException	if <code>action</code>
	 * 			is <code>null</code> or if <code>action.getName()</code> is
	 * 			<code>null</code> or an empty string, or an action with the
	 * 			given name is already registered.
	 */
	public synchronized void registerAction(ActionImpl action) {
		if (null == action)
			throw new IllegalArgumentException("action null");
		if (action.isNull())
			return;
		if (null == action.getName())
			throw new IllegalArgumentException("action.getName() null");
		if ("".equals(action.getName()))
			throw new IllegalArgumentException("action.getName() empty string");

		String key = (String) action.getValue(action.DEFAULT);

		if (getLazyActions().containsKey(key) || getActions().containsKey(key))
			throw new IllegalArgumentException(key + " already registered");

		getActions().put(key, action);
	}

	/**
	 * Register the given action with this application.
	 * <p>
	 * The action will be lazily instantiated when the action is accessed
	 * for the first time.
	 * <p>
	 * The action can be later accessed through 
	 * {@link #performAction(String, Object, int, String)
	 * performAction(String, Object, int, String)} or 
	 * {@link #performAction(String, ActionEvent)
	 * performAction(String, ActionEvent)}
	 * <p>
	 * Just use the <code>name</code> parameter of those methods.
	 * 
	 * @param	name				the name.
	 * @param	actionClassName		the name of the action class to be lazily
	 * 								instantiated.
	 * @throws	IllegalArgumentException	if <code>name</code> is
	 * 			<code>null</code> or an empty string, or if the given
	 * 			<code>actionClassName</code> is not loadable, i.e.
	 * 			<code>Class.forName(actionClassName)</code> throws a
	 * 			<code>ClassNotFoundException</code>, or an action with the
	 * 			given name is already registered.
	 */
	public synchronized void registerAction(String name, String actionClassName) {
		if (null == name)
			throw new IllegalArgumentException("name null");
		if ("".equals(name))
			throw new IllegalArgumentException("name empty string");
		if (getLazyActions().containsKey(name) || getActions().containsKey(name))
			throw new IllegalArgumentException(name + " already registered");

		try {
			Class.forName(actionClassName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.toString());
		}

		getLazyActions().put(name, actionClassName);
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
	 * Answer the map containing the already instantiated in this repository.
	 *
	 * @return	the map.
	 */
	protected Map getActions() {
		if (null == fActions) {
			fActions = new HashMap();
		}

		return fActions;
	}

	/**
	 * Answer the map containing the actions to be lazily instantiated in
	 * this repository.
	 *
	 * @return	the map.
	 */
	protected Map getLazyActions() {
		if (null == fLazyActions) {
			fLazyActions = new HashMap();
		}

		return fLazyActions;
	}

	/**
	 * Try to lazily create an action instance.
	 * <p>
	 * The &quot;action&quot; called <code>name</code> will be removed from
	 * <code>fLazyActions</code> and added to <code>fActions</code> if the
	 * instance has been successfully instantiated.
	 * <p>
	 * <em>Precondition:</em> <code>getLazyActions().containsKey(name)</code>.
	 *
	 * @param	name		the action's name.
	 * @return	the action with the given name or the <code>NullAction</code>
	 * 			if the action cannot be lazily instantiated.
	 */
	protected synchronized ActionImpl getLazyInstance(String name) {
		try {
			String className = (String) getLazyActions().get(name);

			AbstractAction result =
				(AbstractAction) ReflectionUtils.newInstance(className, name, getApp()); 

			getActions().put(name, result);

			getLazyActions().remove(name);

			return result;				
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return NullAction.instance();
		} catch (ClassCastException e) {
			//e.printStackTrace();
			return NullAction.instance();
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
			return NullAction.instance();
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
			return NullAction.instance();
		} catch (InstantiationException e) {
			//e.printStackTrace();
			return NullAction.instance();
		} catch (NoSuchMethodException e) {
			//e.printStackTrace();
			return NullAction.instance();
		}
	}
}