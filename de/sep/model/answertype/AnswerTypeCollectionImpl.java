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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import de.sdavids.beans.BoundBean;

/**
 * JavaBean interface for a collection of <code>IAnswerType</code>s.
 *
 * This implementation functions similar to a <code>List</code>.
 *
 * The collection is thread-safe.
 *
 * JavaBean.
 * 
 * Has the following indexed bound property (readable):
 *
 * <ol>
 *    <li>answers</li>
 * </ol>
 */
public class AnswerTypeCollectionImpl
	extends BoundBean
	implements IAnswerTypeCollection {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** <code>List</code> containing the answers. */
	private List fAnswers;

	//////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Internal interator; implements the <code>IAnswerTypeIterator</code>
	 * interface.
	 *
	 * @see		de.sep.model.questionaire.answer.IAnswerTypeIterator
	 */
	protected class AnswerTypeIterator implements IAnswerTypeIterator {

		/** The &quot;internal&quot; iterator. */
		private Iterator fIterator = fAnswers.iterator();

		/**
		 * Returns <code>true</code> if the iteration has more answers.
		 *
		 * In other words, returns <code>true</code> if <code>next</code>
		 * would return an answer rather than throwing an exception.
		 *
		 * @return	<code>true</code> if the iterator has more answers.
		 */
		public boolean hasNext() {
			return fIterator.hasNext();
		}

		/**
		 * Returns the next answer in the iteration.
		 *
		 * @return	the next answer in the iteration.
		 * @throws	<code>NoSuchElementException</code> iteration has no
		 *        	more answers.
		 */
		public IAnswerType next() throws NoSuchElementException {

			return (IAnswerType) fIterator.next();
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Construct and initialize a new <code>AnswerTypeCollectionImpl</code>.
	 */
	public AnswerTypeCollectionImpl() {
		clear();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Common Interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object's answers
	 * are equivalent to the specified object's answers.
	 *
	 * @param     <code>obj</code> the <code>Object</code> to compare with.
	 * @return    <code>true</code> if both object's answers are equal.
	 */
	public synchronized boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof IAnswerTypeCollection))
			return false;

		IAnswerTypeCollection other = (IAnswerTypeCollection) obj;

		if (size() != other.size())
			return false;

		IAnswerTypeIterator it = other.iterator();

		while (it.hasNext()) {
			if (!contains(it.next()))
				return false;
		}

		return true;
	}

	/**
	 * Generates a hash code for the receiver.
	 *
	 * This method is supported primarily for hash tables, such as those
	 * provided in <code>java.util.Hashtable</code>
	 *
	 * @return	an integer hash code for the receiver.
	 * @see 	java.util.Hashtable.
	 */
	public synchronized int hashCode() {
		return super.hashCode();
	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return	a string representation of the object.
	 */
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer();
		IAnswerTypeIterator it = iterator();

		if (it.hasNext())
			sb.append(it.next().getType());

		while (it.hasNext()) {
			sb.append(" ").append(it.next().getType());
		}

		return sb.toString();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////////   

	/**
	 * Adds the specified answer to the end of this collection.
	 *
	 * @param	<code>answer</code> the answer to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
	 *        	is <code>null</code>.
	 */
	protected synchronized void internalAdd(IAnswerType answers)
		throws IllegalArgumentException {

		internalAdd(answers, size(), true, false);
	}

	/**
	 * Inserts the specified answer at the specified position in this collection.
	 * 
	 * Shifts the answer currently at that position (if any) and any subsequent
	 * answers to the right (adds one to their indices).
	 *
	 * Fires <code>PropertyChangeEvent<code>s whenever a answer is added and
	 * <code>firePropertyChangeEvent<code> is <code>true</code>.
	 *
	 * @param	<code>index</code> the index.
	 * @param	<code>answer</code> the answer to be added.
	 * @param	<code>firePropertyChangeEvent</code> whether a 
	 * 			<code>PropertyChangeEvent<code> should be fired or not.
	 * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
	 *        	is <code>null</code>.
	 * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
	 *  		<code> (index < 0 || index > size())</code>.
	 */
	protected synchronized void internalAdd(
		IAnswerType answer,
		int index, boolean append,
		boolean firePropertyChangeEvent)
		throws IllegalArgumentException, IndexOutOfBoundsException {

		if (null == answer)
			throw new IllegalArgumentException("answer null");

		answer.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(evt);
			}
		});
		
		fAnswers.add((int) (index), answer);
		
		if (firePropertyChangeEvent) {
			if (append) {
				firePropertyChange("answersAppend", new Integer(index), answer);
			} else {
				firePropertyChange("answersInsert", new Integer(index), answer);				
			}			
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPECOLLECTION
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Replace the answers in this collection.
	 * 
	 * @param	the answers.
	 */
	public synchronized void setAnswers(IAnswerType[] answers) {
		clear();
		addAll(answers);
	}

	/**
	 * Replace the answer at the given index.
	 *
	 * @param	<code>index</code> the index value into the answer array.
	 * @param	<code>answer</code> the new answer.
	 */
	public synchronized void setAnswers(int index, IAnswerType answerType) {
		put(answerType, index);
	}

	/**
	 * Adds the specified answer to the end of this collection.
	 *
	 * Fires <code>PropertyChangeEvent<code>s whenever an answer is added.
	 *
	 * @param	<code>answerType</code> the answer to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
	 *        	is <code>null</code>.
	 */
	public synchronized void add(IAnswerType answerType)
		throws IllegalArgumentException {

		internalAdd(answerType, size(), true, true);
	}

	/**
	 * Inserts the specified answer at the specified position in this list.
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
	public synchronized void add(IAnswerType answerType, int index)
		throws IllegalArgumentException, IndexOutOfBoundsException {

		internalAdd(answerType, index, false, true);
	}

	/**
	 * Adds all answers of the specified collection to this collection.
	 *
	 * Fires a <code>PropertyChangeEvent<code> after the collection has been
	 * modified.
	 *
	 * @param	<code>answers</code> the answers to be added.
	 */
	public void addAll(IAnswerTypeCollection answers) {

		if (null == answers)
			return;

		if (0 == answers.size())
			return;

		IAnswerTypeIterator it = answers.iterator();

		while (it.hasNext()) {
			internalAdd(it.next());
		}

		firePropertyChange("answers", null, getAnswers());
	}

	/**
	 * Adds all answers of the specified collection to the end of this
	 * collection.
	 *
	 * Fires a <code>PropertyChangeEvent<code> after the collection has been
	 * modified.
	 *
	 * @param	<code>answers</code> the answers to be added.
	 */
	public void addAll(IAnswerType[] answers) throws IllegalArgumentException {

		if (null == answers)
			return;

		int len = answers.length;

		if (0 == len)
			return;

		for (int i = 0; i < len; i++) {
			internalAdd(answers[i]);
		}

		firePropertyChange("answers", null, getAnswers());
	}

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
	public synchronized IAnswerType remove(int index)
		throws IllegalArgumentException, IndexOutOfBoundsException {

		IAnswerType result = (IAnswerType) fAnswers.remove(index);

		firePropertyChange("answers", null, getAnswers());

		return result;
	}

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
	public synchronized void put(IAnswerType answer, int index)
		throws IllegalArgumentException, IndexOutOfBoundsException {

		if (null == answer)
			throw new IllegalArgumentException("question null");

		fAnswers.set(index, answer);

		firePropertyChange("answers", null, getAnswers());
	}
	
	/**
	 * Removes all answers from this collection.
	 *
	 * This collection will be empty after this method.
	 *
	 * Fires a <code>PropertyChangeEvent<code>.
	 */
	public void clear() {
		fAnswers = new ArrayList();

		firePropertyChange("answers", null, getAnswers());
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the answer at the given index.
	 *
	 * @param	<code>index</code> the index.
	 * @return	the answer.
	 * @throws	<code>IndexOutOfBoundsException</code> if index is out of range
	 *  		<code> (index < 0 || index > size())</code> .
	 */
	public synchronized IAnswerType get(int index)
		throws IndexOutOfBoundsException {

		return (IAnswerType) fAnswers.get(index);
	}

	/**
	 * Returns <code>true</code> if this collection contains the specified
	 * answer.
	 *
	 * More formally, returns <code>true</code> if and only if this
	 * collection contains one answer <code>o</code> such that
	 * <code>(answer==null ? o==null : answer.equals(o))</code>.
	 *
	 * @param	<code>answer</code> the answer whose presence in this
	 *        	collection is to be tested.
	 * @return	<code>true</code> if this collection contains the specified
	 *        	answer.
	 */
	public boolean contains(IAnswerType answer) {
		return fAnswers.contains(answer);
	}

	/**
	 * Answer the answers property value.
	 *
	 * @return	the answers property value.
	 */
	public synchronized IAnswerType[] getAnswers() {
		return toArray();
	}

	/**
	 * Answer the answers index property value.
	 *
	 * @param	<code>index</code> the index value into the property array.
	 * @return	the answers property value.
	 */
	public synchronized IAnswerType getAnswers(int index) {
		return getAnswers()[index];
	}

	/**
	 * Returns <code>true</code> if this collection contains no answers.
	 *
	 * @return	<code>true</code> if this collection is empty.
	 */
	public boolean isEmpty() {
		return (0 == size());
	}

	/**
	 * Returns <code>true</code> if this collection contains at least one
	 * answer.
	 *
	 * @return	<code>true</code> if this collection is not empty.
	 */
	public boolean isNotEmpty() {
		return !isEmpty();
	}

	/**
	 * Returns an iterator over the answers in this collection in proper
	 * sequence.
	 *
	 * @return	An <code>IAnswerTypeIterator</code> over the answers in this
	 *        	collection in proper sequence.
	 * @see	de.sep.model.questionaire.IAnswerTypeIterator.
	 */
	public IAnswerTypeIterator iterator() {
		return new AnswerTypeIterator();
	}

	/**
	 * Returns the number of answers in this collection.
	 *
	 * @return	the number of answers.
	 */
	public int size() {
		return fAnswers.size();
	}

	/**
	 * Searches for the first occurence of the given answer,
	 * testing for equality using the <code>equals</code> method.
	 *
	 * @param	<code>answer</code> the answer whose index is to be determined.
	 * @return	the index of the first occurrence of the argument in this collection;
	 *  		returns <code>-1</code> if the answer is not found.
	 */
	public int indexOf(IAnswerType answer) {
		return fAnswers.indexOf(answer);
	}

	/**
	 * Returns the index of the last occurrence of the specified answer
	 * in this collection.
	 *
	 * @param	<code>answer</code> the answer whose index is to be determined.
	 * @return	the index of the last occurrence of the argument in this collection;
	 *  		returns <code>-1</code> if the answer is not found.
	 * @see	#indexOf
	 */
	public int lastIndexOf(IAnswerType answer) {
		return fAnswers.lastIndexOf(answer);
	}

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
	 * @return    an array containing all of the questions in this collection.
	 */
	public synchronized IAnswerType[] toArray() {
		return (IAnswerType[]) fAnswers.toArray(new IAnswerType[size()]);
	}
}