/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.interfaces;

import java.awt.Frame;
import java.util.ResourceBundle;

import de.sdavids.swing.SwingWorker;
import de.sdavids.swing.actions.ActionRepository;

/**
 * Interface for common application functionality.
 * 
 * @author		Sebastian Davids
 * @version	1.30	02-01-20
 */
public interface IApplication {

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the configuration.
	 *
	 * @return	the configuration.
	 */
	ResourceBundle getConfig();

	/**
	 * Answer the action repository.
	 *
	 * @return	the configuration.
	 */
	ActionRepository getActionRepository();

	/**
	 * Answer the string resource with the given key.
	 *
	 * @param	key		the resource's key.
	 * @return	the string.
	 */
	String getConfig(String key);

	/**
	 * Answer the application's <code>Frame</code>.
	 *
	 * @return	the frame.
	 */
	Frame getFrame();

}