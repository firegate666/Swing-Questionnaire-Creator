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

import de.sdavids.beans.IBoundBean;
import de.sdavids.interfaces.INullable;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IAnswerTypeCollection;
import de.sep.model.answertype.IAnswerTypeIterator;

/**
 * Read/write-interface for a question.
 */
public interface IQuestion extends IBoundBean, INullable, Comparable {

	/* ----------- Mutators --------------------- */

	/**
	 * Set the question's number.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>number</em>.
	 *
	 * @param	<code>number</code> the number.
	 * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
	 *        	positive number.
	 */
	void setNumber(int number) throws IllegalArgumentException;

	/**
	 * Set the question's question text.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>text</em>.
	 *
	 * @param	<code>question</code> the question's question text.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code> is
	 * 			<code>null</code>.
	 */
	void setText(String question) throws IllegalArgumentException;

	/**
	 * Adds the specified answer to the end of the end of this question.
	 *
	 * Fires <code>PropertyChangeEvent<code>s whenever a answer is added.
	 *
	 * @param	<code>answer</code> the answer to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
	 *        	is <code>null</code>.
	 */
	void add(IAnswerType answer) throws IllegalArgumentException;

	/**
	 * Inserts the specified answer at the specified position in this list.
	 * 
	 * Shifts the answer currently at that position (if any) and any subsequent
	 * answers to the right (adds one to their indices).
	 *
	 * Fires <code>PropertyChangeEvent<code>s whenever a answer is added.
	 *
	 * @param	<code>index</code> the index.
	 * @param	<code>question</code> the answer to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
	 *        	is <code>null</code>.
	 * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
	 *  		<code> (index < 0 || index > size())</code>.
	 */
	void add(IAnswerType answer, int index)
		throws IllegalArgumentException, IndexOutOfBoundsException;

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the question's number.
	 *
	 * @return	the number.
	 */
	int getNumber();

	/**
	 * Answer the question's question text.
	 *
	 * @return	the text.
	 */
	String getText();

	/**
	 * Returns <code>true</code> if this collection contains at least one
	 * answerType.
	 *
	 * @return	<code>true</code> if this collection is not empty.
	 */
	boolean hasAnswerTypes();

	/**
	 * Returns <code>true</code> if this question contains no answer types.
	 *
	 * @return	<code>true</code> if this collection is empty.
	 */
	boolean hasNoAnswerTypes();

	/**
	 * Returns an iterator over the answer types in this question in proper
	 * sequence.
	 *
	 * @return	an <code>IAnswerTypeIterator</code> over the answer types in
	 * 			this question in proper sequence.
	 * @see	de.sep.model.answertype.IAnswerTypeIterator.
	 */
	IAnswerTypeIterator iterator();

	/**
	 * Compares two questions.
	 * 
	 * Compares the two questions numbers numerically.
	 *
	 * @param	<code>question</code>   the question to be compared.
	 * @return	the value <code>0</code> if the argument question is equal to
	 *          this question; a value less than <code>0</code> if this
	 *          question smaller than the question argument; and a
	 *          value greater than <code>0</code> if this question is
	 *          numerically greater than the question argument
	 *			(signed comparison).
	 */
	int compareTo(IQuestion question);
}