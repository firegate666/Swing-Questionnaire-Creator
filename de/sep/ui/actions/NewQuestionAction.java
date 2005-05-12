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
 * A <code>NewQuestionaireAction<code> is invoked if the user wants to append a new
 * question to the questionaire.
 */
public class NewQuestionAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>NewQuestionAction<code> object with the specified name
	 * and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public NewQuestionAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when a <code>NewQuestionAction<code> occurs.
	 * 
	 * Adds a new question to the the end of the model.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		((Questionaire) getApp()).getFactory().appendQuestion(((Questionaire) getApp()).getCurrentDoc());

		performAction(ACTION_SCROLL_BOTTOM, this, hashCode(), "questionPanelAdd");
	}
}