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

import java.awt.event.ActionEvent;import de.sep.model.questionaire.IQuestionaire;import de.sep.ui.Questionaire;import de.sep.ui.dialog.ExitProgramDialog;

/**
 * A <code>ExitAction<code> is invoked if the user wants to close the
 * application.
 *
 * Invokes the <code>CloseAction<code>.
 *
 * @see		de.sep.ui.action.CloseAction.
 */
public class ExitAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>ExitAction<code> object with the specified name and a
	 * reference to the application.
	 *
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application.
	 */
	public ExitAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>ExitAction<code> occurs.
	 * 	
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {     	performAction(ACTION_CLOSE, evt);		getFrame().dispose();		System.exit(0);	}
}