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
 * A <code>AutoReorderQuestionsAction<code> is invoked if the user wants the 
 * application to automatically reorder (sort ascending) all questions of a 
 * questionaire upon changing a questions number or adding/removing a question
 * to/from a questionaire.
 */
public class AutoReorderQuestionsAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>AutoReorderQuestionsAction<code> object with the
	 * specified name and a reference to the application.
	 * 
	 * @param	<code>name<code> the description.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public AutoReorderQuestionsAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>AutoReorderQuestionsAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setSelected(!isSelected());
		((Questionaire) getApp()).getQuestionairePanel().reorderQuestions();
	}
}