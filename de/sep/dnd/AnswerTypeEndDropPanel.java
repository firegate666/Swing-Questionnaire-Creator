package de.sep.dnd;

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

import java.awt.Color;

import de.sep.ui.question.QuestionPanel;

/**
 * The <code>AnswerTypeEndDropPanel</code> is similar to a
 * <code>AnswerTypeDropPanel</code> but appends answer types to the end of the
 * question.
 * 
 * It's main use is to make it possible to drag answer types to the end of the
 * panel.
 * 
 * If a <code>AnswerTypeTransferable</code> is dropped over a
 * <code>AnswerTypeEndDropPanel</code> a new answer type is appended to the end
 * of the question.
 */
public class AnswerTypeEndDropPanel extends AnswerTypeDropPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The original background color of the parent panel. */
	private Color fOriginalParentColor;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////    

	/**
	 * Create a new <code>QuestionEndDropPanel</code> with the default
	 * &quot;highlight&quot; color orange.
	 * 
	 * @param	<code>questionPanel</code> the question panel this answer type
	 * 			panel belongs to.
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public AnswerTypeEndDropPanel(QuestionPanel questionPanel) {
		this(questionPanel, Color.orange);
	}

	/**
	 * Create a new <code>QuestionEndDropPanel</code> with the given
	 * &quot;highlight&quot; color.
	 * 
	 * @param	<code>questionPanel</code> the question panel this answer type
	 * 			panel belongs to.
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public AnswerTypeEndDropPanel(QuestionPanel questionPanel, Color highlight) {
		super(questionPanel, highlight);
		
		initialize();	
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Intiate the update of the model.
	 * 
	 * Unselects the panel and fires a <code>PropertyChange</code> event called <em>append</em>.
	 * 
	 * Use the event's <em>getNewValue()</em> to retrieve the dropped answer types code.
	 */
	protected void dropIt(String data) {
		showUnselected();

		firePropertyChange(
			"appendAnswerType",
			"",
			data);
	}
}