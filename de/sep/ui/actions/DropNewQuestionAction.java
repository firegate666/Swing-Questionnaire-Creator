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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.sep.ui.Questionaire;
import de.sep.ui.question.QuestionPanel;
import de.sep.ui.questionaire.QuestionairePanel;

/**
 * A <code>NewQuestionaireAction<code> is invoked if the user wants to create a new
 * questionaire.
 */
public class DropNewQuestionAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>NewQuestionaireAction<code> object with the specified name
	 * and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public DropNewQuestionAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER 
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when a <code>DropNewQuestionaireAction<code> occurs.
	 * 
	 * Adds a new question to the the end of the model.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		Component source = (Component) evt.getSource();

		QuestionairePanel parent = (QuestionairePanel) source.getParent();

		List components = new ArrayList(Arrays.asList(parent.getComponents()));

		QuestionPanel qp =
			(QuestionPanel) parent.getComponent(components.indexOf(source) + 1);

		((Questionaire) getApp()).getFactory().insertQuestion(
			((Questionaire) getApp()).getCurrentDoc(),
			qp.getQuestion().getNumber());
	}
}