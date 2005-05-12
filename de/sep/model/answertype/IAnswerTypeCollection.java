package de.sep.model.answertype;

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

/**
 * Interface for a collection of answers.
 * 
 * This interface supports the following indexed bound properties:
 *
 * <ol>
 *    <li>answers</li>
 * </ol>
 */
public interface IAnswerTypeCollection extends IBoundBean {

    //////////////////////////////////////////////////////////////////////////////
    // BEAN PROPERTIES
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */

    /**
     * Replace the answers in this collection.
     * 
     * @param	the answers.
     */
    void setAnswers(IAnswerType[] answers);

    /**
     * Replace the answer at the given index.
     *
     * @param	<code>index</code> the index value into the answer array.
     * @param	<code>answer</code> the new answer.
     */
    void setAnswers(int index, IAnswerType answer);

    /* ----------- Accessors -------------------- */

    /**
     * Answer an array containing the answers in this collection.
     * 
     * The returned array will <strong>not</strong> be &quot;safe&quot;
     * in that a references to it is maintained by this collection.
     * 
     * @return	the answers.
     */
    public IAnswerType[] getAnswers();

    /**
     * Answer the answer at the given index.
     *
     * @param	<code>index</code> the index value into the answer array.
     * @return	the answer.
     */
    public IAnswerType getAnswers(int index);

    //////////////////////////////////////////////////////////////////////////////
    // PUBLIC INTERFACE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */

    /**
     * Adds the specified answer to the end of the end of this collection.
     *
     * Fires <code>PropertyChangeEvent<code>s whenever a answer is added.
     *
     * @param	<code>answerType</code> the answer to be added.
     * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
     *        	is <code>null</code>.
     */
    void add(IAnswerType answerType);

    /**
     * Inserts the specified answer at the specified position in this collection.
     * 
     * Shifts the answer currently at that position (if any) and any subsequent
     * answers to the right (adds one to their indices).
     *
     * Fires <code>PropertyChangeEvent<code>s whenever a answer is added.
     *
     * @param	<code>index</code> the index.
     * @param	<code>answerType</code> the answer to be added.
     * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
     *        	is <code>null</code>.
     * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
     *  		<code> (index < 0 || index > size())</code>.
     */
    void add(IAnswerType answerType, int index)
        throws IllegalArgumentException, IndexOutOfBoundsException;

    /**
     * Adds all answers of the specified collection to the end of this
     * collection.
     *
     * Fires a <code>PropertyChangeEvent<code> after the collection has been
     * modified.
     *
     * @param	<code>answers</code> the answers to be added.
     */
    void addAll(IAnswerTypeCollection answers);

    /**
     * Adds all answers of the specified collection to the end of this
     * collection.
     *
     * Fires a <code>PropertyChangeEvent<code> after the collection has been
     * modified.
     *
     * @param	<code>answers</code> the answers to be added.
     */
    void addAll(IAnswerType[] answers);

    /**
     * Replaces the answer at the specified position in this collection with the
     * specified question.
     *
     * Fires <code>PropertyChangeEvent<code>s whenever a answer is replaced.
     *
     * @param	<code>index</code> the index.
     * @param	<code>question</code> the new answer.
     * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
     *        	is <code>null</code>.
     * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
     *  		<code> (index < 0 || index > size())</code>.
     */
    void put(IAnswerType answer, int index)
        throws IllegalArgumentException, IndexOutOfBoundsException;

    /**
     * Remove the question at the specified index form this collection.
     *
     * Fires <code>PropertyChangeEvent<code>s whenever a answer is removed.
     *
     * @param	<code>index</code> the index.
     * @return	<code>answer</code> the answer that has been removed.
     * @throws	<code>IllegalArgumentException</code> if <code>question</code>
     *        	is <code>null</code>.
     * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
     *  		<code> (index < 0 || index > size())</code>.
     */
    IAnswerType remove(int index)
        throws IllegalArgumentException, IndexOutOfBoundsException;

    /**
     * Removes all answers from this collection.
     *
     * This collection will be empty after this method.
     *
     * Fires a <code>PropertyChangeEvent<code>.
     */
    void clear();

    /* ----------- Accessors -------------------- */

    /**
     * Answer the answer at the given index.
     *
     * @param	<code>index</code> the index.
     * @return	the answer.
     */
    IAnswerType get(int index);

    /**
     * Returns <code>true</code> if this collection contains the specified
     * answer.
     *
     * More formally, returns <code>true</code> if and only if this
     * collection contains one answer <code>o</code> such that
     * <code>(question==null ? o==null : question.equals(o))</code>.
     *
     * @param	<code>answer</code> the answer whose presence in this
     *        	collection is to be tested.
     * @return	<code>true</code> if this collection contains the specified
     *        	answer.
     */
    boolean contains(IAnswerType answer);

    /**
     * Returns <code>true</code> if this collection contains no answers.
     *
     * @return	<code>true</code> if this collection is empty.
     */
    boolean isEmpty();

    /**
     * Returns <code>true</code> if this collection contains at least one
     * answer.
     *
     * @return	<code>true</code> if this collection is not empty.
     */
    boolean isNotEmpty();

    /**
     * Returns an iterator over the answers in this collection in proper
     * sequence.
     *
     * @return	An <code>IAnswerTypeIterator</code> over the answers in this
     * 			collection in proper sequence.
     * @see		de.sep.model.questionaire.answertype.IAnswerTypeIterator.
     */
    IAnswerTypeIterator iterator();

    /**
     * Returns the number of answers in this collection.
     *
     * @return	the number of answers.
     */
    int size();

    /**
     * Searches for the first occurence of the given answer,
     * testing for equality using the <code>equals</code> method.
     *
     * @param	<code>answer</code> the answer whose index is to be determined.
     * @return	the index of the first occurrence of the argument in this collection;
     *  		returns <code>-1</code> if the answer is not found.
     */
    int indexOf(IAnswerType answer);

    /**
     * Returns the index of the last occurrence of the specified answer
     * in this collection.
     *
     * @param	<code>answer</code> the answer whose index is to be determined.
     * @return	the index of the last occurrence of the argument in this collection;
     *  		returns <code>-1</code> if the answer is not found.
     * @see		indexOf
     */
    int lastIndexOf(IAnswerType answer);

    /**
     * Returns an array containing all of the answers in this collection.
     *
     * The returned array will be &quot;safe&quot; in that no references to
     * it are maintained by this collection. (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return    an array containing all of the answers in this collection.
     */
    IAnswerType[] toArray();
}