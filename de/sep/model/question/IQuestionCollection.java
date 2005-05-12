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

import de.sdavids.beans.IBoundBean;

/**
 * Interface for a collection of questions.
 */
public interface IQuestionCollection extends IBoundBean {

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Adds the specified question to this collection.
	 *
	 * If there's already a question with the given number this method increases
	 * the &quot;old&quot; question's number by one; if this creates another conflict
	 * continue shifting.
	 * 
	 * Either fires a <code>PropertyChangeEvent<code> called <em>questionsAdd</em>
	 * if there has <strong>not<strong> been question with the given question's
	 * number or <em>questionsInsert</em> if there has been a question with the
	 * given question's number.
	 *
	 * @param	<code>question</code> the question to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>.
	 */
	void add(IQuestion question) throws IllegalArgumentException;

	/**
	 * Adds all questions of the specified collection to the end of this
	 * collection.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsAddAll</em>after
	 * the collection has been modified.
	 *
	 * @param	<code>questions</code> the questions to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>questions</code>
	 *        	is <code>null</code>.
	 */
	void addAll(IQuestionCollection questions) throws IllegalArgumentException;

	/**
	 * Adds all questions of the specified collection to the end of this
	 * collection.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsAddAll</em>after
	 * the collection has been modified.
	 *
	 * @param	<code>questions</code> the questions to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>questions</code>
	 *        	is <code>null</code>.
	 */
	void addAll(IQuestion[] questions) throws IllegalArgumentException;

	/**
	 * Remove the given question from this collection.
	 *
	 * Fires <code>PropertyChangeEvent<code>s called <em>questionsRemove</em>
	 * whenever a question is removed.
	 *
	 * @param	<code>question</code> the question.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>
	 * @throws <code>NoSuchElementException</code> if the collection does not
	 * 			contain the given question.
	 */
	void remove(IQuestion question)
		throws IllegalArgumentException, NoSuchElementException;

	/**
	 * Removes all questions from this collection.
	 *
	 * This collection will be empty after this method.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsClear</em>.
	 */
	void clear();

	/* ----------- Accessors -------------------- */

	/**
	 * Returns <code>true</code> if this collection contains the specified
	 * question.
	 *
	 * More formally, returns <code>true</code> if and only if this
	 * collection contains one question <code>o</code> such that
	 * <code>(question==null ? o==null : question.equals(o))</code>.
	 *
	 * @param	<code>question</code> the question whose presence in this
	 *        	collection is to be tested.
	 * @return	<code>true</code> if this collection contains the specified
	 *        	question.
	 */
	boolean contains(IQuestion question);

	/**
	 * Returns <code>true</code> if this collection contains a question with
	 * the specified number.
	 *
	 * @param	<code>number</code> the question' number whose presence in
	 *          this collection is to be tested.
	 * @return	<code>true</code> if this collection contains a question
	 * 			with the specified number.
	 */
	public boolean contains(int number);

	/**
	 * Returns the question with the specified number.
	 *
	 * @param	<code>number</code> the question' number.
	 * @throws	<code>NoSuchElementException</code> if this collection does
	 *			not contain aquestion with the specified number.
	 */
	public IQuestion get(int number) throws NoSuchElementException;

	/**
	 * Returns <code>true</code> if this collection contains no questions.
	 *
	 * @return	<code>true</code> if this collection is empty.
	 */
	boolean isEmpty();

	/**
	 * Returns <code>true</code> if this collection contains at least one
	 * question.
	 *
	 * @return	<code>true</code> if this collection is not empty.
	 */
	boolean isNotEmpty();

	/**
	 * Returns an iterator over the questions in this collection in proper
	 * sequence.
	 *
	 * @return	an <code>IQuestionIterator</code> over the questions in this
	 * 			collection in proper sequence.
	 * @see	de.sep.model.question.IQuestionIterator.
	 */
	IQuestionIterator iterator();

	/**
	 * Returns the number of questions in this collection.
	 *
	 * @return	the number of questions.
	 */
	int size();

	/**
	 * Returns an array containing all of the questions in this collection.
	 *
	 * The returned array will be &quot;safe&quot; in that no references to
	 * it are maintained by this collection. (In other words, this method must
	 * allocate a new array even if this collection is backed by an array).
	 * The caller is thus free to modify the returned array.
	 *
	 * This method acts as bridge between array-based and collection-based
	 * APIs.
	 *
	 * @return	an array containing all of the questions in this collection.
	 */
	IQuestion[] toArray();
}