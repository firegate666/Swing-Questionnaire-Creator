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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import de.sdavids.beans.BoundBean;

/**
 * JavaBean interface for a collection of <code>IQuestion</code>s.
 *
 * This implementation functions similar to a <code>SortedMap</code>.
 * 
 * Changes of the <em>number</em> property of a question contained in this
 * collection lead to the following reorganization:
 * 
 * If there's already a question in this collection with the new number of the
 * question firing the <code>PropertyChangeEvent</code>then that question gets
 * the old number of the question firing the event, i.e. the numbers
 * are swapped; otherwise the question's number is just updated.
 *
 * The collection is thread-safe.
 *
 * JavaBean.
 * 
 * Has the following R/W bound property:
 *
 * <ol>
 *    <li>questions</li>
 * </ol>
 */
class QuestionCollectionImpl
	extends BoundBean
	implements IQuestionCollection {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** <code>SortedMap</code> containing the questions. */
	private SortedMap fQuestions;

	/**
	 * Whether we should listen to changes in a question or not.
	 * 
	 * @see #update
	 * @see #shift
	 */
	private transient boolean fListen;

	//////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Internal interator; implements the <code>IQuestionIterator</code>
	 * interface.
	 *
	 * @see	de.sep.model.questionaire.question.IQuestionIterator
	 */
	protected class QuestionIterator implements IQuestionIterator {

		//////////////////////////////////////////////////////////////////////////
		// ATTRIBUTES
		//////////////////////////////////////////////////////////////////////////

		/* ----------- Instance Attributes ---------- */

		/** The &quot;internal&quot; iterator. */
		private Iterator fIterator = fQuestions.keySet().iterator();

		//////////////////////////////////////////////////////////////////////////
		// PUBLIC INTERFACE
		//////////////////////////////////////////////////////////////////////////

		/* ----------- Mutators --------------------- */

		/**
		 * Returns the next question in the iteration.
		 *
		 * @return	the next question in the iteration.
		 * @throws	<code>NoSuchElementException</code> iteration has no
		 *        	more questions.
		 */
		public IQuestion next() throws NoSuchElementException {
			return (IQuestion) fQuestions.get(fIterator.next());
		}

		/* ----------- Accessors -------------------- */

		/**
		 * Returns <code>true</code> if the iteration has more questions.
		 *
		 * In other words, returns <code>true</code> if <code>next</code>
		 * would return an question rather than throwing an exception.
		 *
		 * @return	<code>true</code> if the iterator has more questions.
		 */
		public boolean hasNext() {
			return fIterator.hasNext();
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Construct and initialize a new <code>AnswerTypeCollectionImpl</code>.
	 */
	public QuestionCollectionImpl() {
		clear();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Common Interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object's questions
	 * are equivalent to the specified object's questions.
	 *
	 * @param     <code>obj</code> the <code>Object</code> to compare with.
	 * @return    <code>true</code> if both object's questions are equal.
	 */
	public synchronized boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof IQuestionCollection))
			return false;

		IQuestionCollection other = (IQuestionCollection) obj;

		if (size() != other.size())
			return false;

		IQuestionIterator it = other.iterator();

		while (it.hasNext()) {
			if (!contains(it.next()))
				return false;
		}

		return true;
	}

	/**
	 * Generates a hash code for the receiver.
	 *
	 * This method is supported primarily for maps, such as those
	 * provided in <code>java.util.Map</code>
	 *
	 * @return	an integer hash code for the receiver.
	 * @see 	java.util.Map.
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
		IQuestionIterator it = iterator();

		if (it.hasNext()) {
			sb.append(it.next());
		}

		while (it.hasNext()) {
			sb.append(" ").append(it.next());
		}

		return sb.toString();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
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
	 * given question's number; the <code>newValue</code> of the event is
	 * <code>question</code>.
	 *
	 * @param	<code>question</code> the question to be added.
	 * @param	<code>firePropertyChangeEvent</code> if <code>true</code> the events
	 * 			described above will be fired, otherwise no events will be fired.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>.
	 */
	protected synchronized void internalAdd(
		IQuestion question,
		boolean firePropertyChangeEvent)
		throws IllegalArgumentException {

		if (null == question)
			throw new IllegalArgumentException("question null");

		int n = question.getNumber();
		Integer newNumber = new Integer(n);

		boolean alreadyThere = fQuestions.containsKey(newNumber);

		if (alreadyThere)
			shift(n);

		fQuestions.put(newNumber, question);

		question.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("number".equals(evt.getPropertyName())) {
					if (fListen)
						update((IQuestion) evt.getSource(), ((Integer) evt.getOldValue()).intValue());
				}

				firePropertyChange(evt);
			}
		});

		if (firePropertyChangeEvent) {
			if (alreadyThere) {
				firePropertyChange("questionsInsert", null, question);
			} else {
				firePropertyChange("questionsAdd", null, question);
			}
		}
	}

	/**
	 * Remove the given question from this collection.
	 *
	 * Fires <code>PropertyChangeEvent<code>s called <em>questionsRemove</em>
	 * whenever a question is removed; the <code>oldValue</code> of the event is
	 * <code>question</code>.
	 *
	 * @param	<code>question</code> the question.
	 * @param	<code>firePropertyChangeEvent</code> if <code>true</code> the events
	 * 			described above will be fired, otherwise no events will be fired.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>
	 * @throws <code>NoSuchElementException</code> if the collection does not
	 * 			contain the given question.
	 */
	protected synchronized IQuestion internalRemove(
		IQuestion question,
		boolean firePropertyChangeEvent)
		throws IllegalArgumentException {

		if (null == question)
			throw new IllegalArgumentException("question null");
		if (!contains(question))
			throw new NoSuchElementException(question.toString());

		IQuestion removed =
			(IQuestion) fQuestions.remove(new Integer(question.getNumber()));

		if ((null != removed) && firePropertyChangeEvent)
			firePropertyChange("questionsRemove", question, null);

		return removed;
	}

	/**
	 * Increase the numbers of all questions with a number greater or equal to the
	 * given number by one.
	 *
	 * To counter an infinite loop we should <code>not</code> listen to changes of the
	 * <em>number</em> property of the questions while we are shifting.
	 * 
	 * @param	<code>number</code> the new number.
	 */
	protected synchronized void shift(int number) {
		int currentNumber = number;

		fListen = false;

		IQuestion q =
			internalRemove((IQuestion) fQuestions.get(new Integer(currentNumber)), false);

		while (null != q) {
			currentNumber++;

			q.setNumber(currentNumber);

			q = (IQuestion) fQuestions.put(new Integer(currentNumber), q);
		}

		fListen = true;
	}

	/**
	 * Updates the question within this collection.
	 *
	 * If there's already a question in this collection with the new number of the
	 * given question that question gets the <code>oldNumber</code>, i.e. the numbers
	 * are swapped.
	 * 
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsUpdated</em>
	 * after the update; the <code>newValue</code> of the event is
	 * <code>question</code>.
	 *
	 * To counter an infinite loop we should <code>not</code> listen to changes of the
	 * <em>number</em> property of the questions while we are shifting.
	 * 
	 * Precondition: <code>question != null</code> and element of this collection.
	 * 
	 * @param	<code>question</code> the question.
	 * @param	<code>oldNumber</code> the old number.
	 */
	protected synchronized void update(IQuestion question, int oldNumber) {
		Integer newNumber = new Integer(question.getNumber());
		Integer old = new Integer(oldNumber);

		if (fQuestions.containsKey(newNumber)) {
			IQuestion oldQuestion = (IQuestion) fQuestions.put(newNumber, question);

			fListen = false;

			oldQuestion.setNumber(oldNumber);

			fListen = true;

			fQuestions.put(old, oldQuestion);
		} else {
			fQuestions.put(newNumber, fQuestions.remove(new Integer(oldNumber)));
		}

		firePropertyChange("questionsUpdated", null, question);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IQUESTIONCOLLECTION
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Replace the questions in this collection.
	 * 
	 * Fires a <code>PropertyChangeEvent<code> called <em>questions</em> after
	 * the collection has been modified.
	 * 
	 * @param	the questions.
	 */
	public synchronized void setQuestions(IQuestion[] questions) {
		clear();

		addAll(questions);

		firePropertyChange("questions", null, getQuestions());
	}

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
	 * given question's number; the <code>newValue</code> of the event is
	 * <code>question</code>.
	 *
	 * @param	<code>question</code> the question to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>.
	 */
	public synchronized void add(IQuestion question)
		throws IllegalArgumentException {

		internalAdd(question, true);
	}

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
	public synchronized void addAll(IQuestionCollection questions) {
		if (null == questions)
			throw new IllegalArgumentException("questions null");

		if (0 == questions.size())
			return;

		IQuestionIterator it = questions.iterator();

		while (it.hasNext()) {
			internalAdd(it.next(), false);
		}

		firePropertyChange("questionsAddAll", null, getQuestions());
	}

	/**
	 * Adds all questions of the specified array to the end of this collection.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsAddAll</em>after
	 * the collection has been modified.
	 *
	 * @param	<code>questions</code> the questions to be added.
	 * @throws	<code>IllegalArgumentException</code> if <code>questions</code>
	 *        	is <code>null</code>.
	 */
	public synchronized void addAll(IQuestion[] questions)
		throws IllegalArgumentException {

		if (null == questions)
			throw new IllegalArgumentException("questions null");

		int len = questions.length;

		if (0 == len)
			return;

		for (int i = 0; i < len; i++) {
			internalAdd(questions[i], false);
		}

		firePropertyChange("questionsAddAll", null, getQuestions());
	}

	/**
	 * Remove the given question from this collection.
	 *
	 * Fires <code>PropertyChangeEvent<code>s called <em>questionsRemove</em>
	 * whenever a question is removed; the <code>oldValue</code> of the event is
	 * <code>question</code>.
	 *
	 * @param	<code>question</code> the question.
	 * @throws	<code>IllegalArgumentException</code> if <code>question</code>
	 *        	is <code>null</code>
	 * @throws <code>NoSuchElementException</code> if the collection does not
	 * 			contain the given question.
	 */
	public synchronized void remove(IQuestion question)
		throws IllegalArgumentException, NoSuchElementException {

		internalRemove(question, true);
	}

	/**
	 * Removes all questions from this collection.
	 *
	 * This collection will be empty after this method.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questionsClear</em>.
	 */
	public void clear() {
		fQuestions = Collections.synchronizedSortedMap(new TreeMap());
		fListen = true;

		firePropertyChange("questionsClear", null, getQuestions());
	}

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
	public boolean contains(IQuestion question) {
		if (null == question)
			return false;

		return fQuestions.containsValue(question);
	}

	/**
	 * Returns <code>true</code> if this collection contains the a
	 * question with the specified number.
	 *
	 * @param	<code>number</code> the question' number whose presence in
	 *          this collection is to be tested.
	 * @return	<code>true</code> if this collection contains a question
	 * 			with the specified number.
	 */
	public boolean contains(int number) {
		return fQuestions.containsKey(new Integer(number));
	}

	/**
	 * Answer the questions property value.
	 *
	 * @return	the questions property value.
	 */
	public synchronized IQuestion[] getQuestions() {
		return toArray();
	}

	/**
	 * Returns the question with the specified number.
	 *
	 * @param	<code>number</code> the question' number.
	 * @throws	<code>NoSuchElementException</code> if this collection does
	 *			not contain a question with the specified number.
	 */
	public synchronized IQuestion get(int number) throws NoSuchElementException {
		if (!contains(number))
			throw new NoSuchElementException("" + number);

		return (IQuestion) fQuestions.get(new Integer(number));
	}

	/**
	 * Answer the questions index property value.
	 *
	 * @param	<code>index</code> the index value into the property array.
	 * @return	the questions property value.
	 */
	public synchronized IQuestion getQuestions(int index) {
		return getQuestions()[index];
	}

	/**
	 * Returns <code>true</code> if this collection contains no questions.
	 *
	 * @return	<code>true</code> if this collection is empty.
	 */
	public boolean isEmpty() {
		return (0 == size());
	}

	/**
	 * Returns <code>true</code> if this collection contains at least one
	 * question.
	 *
	 * @return	<code>true</code> if this collection is not empty.
	 */
	public boolean isNotEmpty() {
		return !isEmpty();
	}

	/**
	 * Returns an iterator over the questions in this collection in proper
	 * sequence.
	 *
	 * @return	an <code>IQuestionIterator</code> over the questions in this
	 *        	collection in proper sequence.
	 * @see	de.sep.model.questionaire.IQuestionIterator.
	 */
	public IQuestionIterator iterator() {
		return new QuestionIterator();
	}

	/**
	 * Returns the number of questions in this collection.
	 *
	 * @return	the number of questions.
	 */
	public int size() {
		return fQuestions.size();
	}

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
	public synchronized IQuestion[] toArray() {
		return (IQuestion[]) fQuestions.values().toArray(new IQuestion[size()]);
	}
}