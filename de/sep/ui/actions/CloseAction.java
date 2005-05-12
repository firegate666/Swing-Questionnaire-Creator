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
 * A <code>CloseAction<code> is invoked if the user wants to close the current
 * questionaire.
 *
 * The questionaire will be saved.
 */
public class CloseAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>CloseAction<code> object with the specified name and a
	 * reference to the application.
	 *
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application.
	 */
	public CloseAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>CloseAction<code> occurs.
	 *
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		performAction(ACTION_SAVE, evt);
		
		((Questionaire) getApp()).setCurrentDoc(((Questionaire) getApp()).getFactory().getNullQuestionaire());
	}
}