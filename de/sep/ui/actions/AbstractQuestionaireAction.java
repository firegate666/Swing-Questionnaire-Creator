package de.sep.ui.actions;

/*
 * SEP-Projekt WS 2001-2002 -- Questionaire
 * 
 * Projektteilnehmer:
 * Marco Behnke <marco@firegate.de>
 * Sebastian Davids <sdavids@gmx.de>
 * Martin Koose <martin@koose-hh.de>
 * 
 * Projektbegleitung: 
 * Prof. Dr. Bernd Kahlbrandt <Bernd.Kahlbrandt@informatik.fh-hamburg.de>
 * 
 * Copyright (c)2001 Behnke, Davids & Koose. Alle Rechte vorbehalten.
 * ===========================================================================
 */

import de.sdavids.interfaces.IApplication;
import de.sdavids.swing.actions.AbstractAction;

/**
 * @author	Sebastian Davids
 * @version	1.00	02-01-20
 */
public abstract class AbstractQuestionaireAction
	extends AbstractAction
	implements IQuestionaireActions {

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>AbstractAction<code> object with the specified
	 * name and a reference to the application.
	 *
	 * @param	name	the name.
	 * @param	app		a reference to the application.
	 * @throws	IllegalArgumentException	if <code>null == app</code>,
	 * 			<code>null == app.getConfig()</code> or if
	 * 			<code>null == app.getFrame()</code>.
	 */
	protected AbstractQuestionaireAction(String name, IApplication app)
		throws IllegalArgumentException {

		super(name, app);
	}
}