package de.sep.model.questionaire;

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
import de.sdavids.interfaces.IAuthored;
import de.sdavids.interfaces.INullable;
import de.sdavids.interfaces.ITitled;
import de.sep.model.question.IQuestion;
import de.sep.model.question.IQuestionCollection;
import de.sep.model.question.IQuestionIterator;

/**
 * Read/write-interface for a questionaire.
 *
 * This interface supports the following simple bound properties:
 *
 * <ol>
 *    <li>title</li>
 * 	  <li>author</li>
 * </ol>
 */
public interface IQuestionaire extends IBoundBean, INullable, ITitled, IAuthored {

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
	
    /* ----------- Accessors -------------------- */

	/**
	 * Returns <code>true</code> if this questionaire contains the specified
	 * question.
	 *
	 * More formally, returns <code>true</code> if and only if this
	 * questionaire contains one question <code>o</code> such that
	 * <code>(question==null ? o==null : question.equals(o))</code>.
	 *
	 * @param	<code>question</code> the question whose presence in this
	 *        	questionaire is to be tested.
	 * @return	<code>true</code> if this questionaire contains the specified
	 *        	question.
	 */
	boolean contains(IQuestion question);

	/**
	 * Returns an iterator over the questions in this questionaire in proper
	 * sequence.
	 *
	 * @return	an <code>IQuestionIterator</code> over the questions in this
	 * 			collection in proper sequence.
	 * @see	de.sep.model.question.IQuestionIterator.
	 */
	IQuestionIterator iterator();

	/**
	 * Answer the number of questions this questionaire contains.
	 *
	 * @return	the number of questions.
	 */
	int numberOfQuestions();
			
	/**
	 * Returns <code>true</code> if this questionaire contains no questions.
	 *
	 * @return	<code>true</code> if this questionaire is empty.
	 */
	boolean isEmpty();

	/**
	 * Returns <code>true</code> if this questionaire contains at least one
	 * question.
	 *
	 * @return	<code>true</code> if this questionaire is not empty.
	 */
	boolean isNotEmpty();
}