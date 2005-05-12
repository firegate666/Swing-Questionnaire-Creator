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

import java.awt.event.ActionEvent;

import de.sep.ui.Questionaire;

/**
 * A <code>DeleteQuestionAction<code> is invoked if the user to delete the selected
 * question(s).
 */
public class DeleteQuestionAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines a <code>DeleteQuestionAction<code> object with the specified name and a
	 * reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public DeleteQuestionAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>DeleteQuestionAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		//dummy
	}
}