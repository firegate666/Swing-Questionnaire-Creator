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

import de.sep.model.answertype.AnswerType;
import de.sep.ui.Questionaire;
import de.sep.ui.answertype.AnswerTypesPanel;
import de.sep.ui.question.QuestionPanel;

/**
 * A <code>NewAnswerTypeAction<code> is invoked if the user wants to append a new
 * answer type to a question.
 */
public class NewAnswerTypeAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>NewAnswerTypeAction<code> object with the specified name
	 * and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public NewAnswerTypeAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when a <code>NewAnswerTypeAction<code> occurs.
	 * 
	 * Adds a new question to the the end of the model.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		//the fContentPnl of QuestionPanel has to be added directly 
		//to it's content pane for this to work	
		
		QuestionPanel source =
			((AnswerTypesPanel) evt.getSource()).getQuestionPanel();

		((Questionaire) getApp()).getFactory().appendAnswerType(
			AnswerType.lookup(Integer.parseInt(evt.getActionCommand())), source.getQuestion());
	}
}