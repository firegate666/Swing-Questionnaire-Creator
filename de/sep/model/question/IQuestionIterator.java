package de.sep.model.question;

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

import java.util.NoSuchElementException;

/**
 * An iteration over an <code>IQuestionCollection</code>.
 *
 * @see	de.sep.model.question.IQuestionCollection
 */
public interface IQuestionIterator {

	/* ----------- Mutators --------------------- */

	/**
	 * Returns the next question in the iteration.
	 *
	 * @return	the next question in the iteration.
	 * @throws	<code>NoSuchElementException</code> iteration has no
	 *        	more questions.
	 */
	IQuestion next() throws NoSuchElementException;

	/* ----------- Accessors -------------------- */

	/**
	 * Returns <code>true</code> if the iteration has more questions.
	 *
	 * In other words, returns <code>true</code> if <code>next</code>
	 * would return an question rather than throwing an exception.
	 *
	 * @return	<code>true</code> if the iterator has more questions.
	 */
	boolean hasNext();
}