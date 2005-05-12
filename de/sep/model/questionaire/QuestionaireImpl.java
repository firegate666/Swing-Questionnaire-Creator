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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.sdavids.beans.BoundBean;
import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.dataobjects.person.PersonFactory;
import de.sep.model.question.IQuestion;
import de.sep.model.question.IQuestionCollection;
import de.sep.model.question.IQuestionIterator;
import de.sep.model.question.QuestionFactory;

/**
 * Implementation of the <code>IQuestionaire</code> interface.
 *
 * JavaBean.
 *
 * Has the following simple bound properties (readable & writeable):
 *
 * <ol>
 *    <li>author</li>
 *    <li>title</li>
 * </ol>
 */
class QuestionaireImpl extends BoundBean implements IQuestionaire {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/**
	 * The questionaire's title.
	 *
	 * R/W bound property.
	 */
	private String fTitle;

	/**
	 * The questionaire's author.
	 *
	 * R/W bound property.
	 */
	private IPerson fAuthor;

	/**
	 * The questionaire's questions.
	 *
	 * R/W bound property.
	 */
	private IQuestionCollection fQuestions;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * No-argument constructor.
	 * 
	 * <code>getTitle</code> will return an empty string.
	 * <code>getQuestions</code> will return an empty
	 * <code>IQuestionCollection</code>.
	 * <code>getAuthor</code> will return the <code>NullIPerson</code>.
	 */
	public QuestionaireImpl() {
		//cannot use working constructor because title could not be set
		//to an empty string through setTitle
		fTitle = "";
		setQuestions(QuestionFactory.createCollection());
		setAuthor(PersonFactory.getNullPerson());
	}

	/**
	 * Construct and initialize a new <code>QuestionaireImpl</code>.
	 *
	 * The <code>author</code> property is set to the <code>NullIPerson</code>;
	 * the <code>questions</code> property is set to an empty
	 * <code>IQuestionCollection</code>.
	 * 
	 * @param	<code>title</code> the questionaire's title.
	 * @throws	<code>IllegalArgumentException</code> if <code>title</code> is an
	 * 			empty string or <code>null</code>.
	 */
	public QuestionaireImpl(String title) throws IllegalArgumentException {
		this(title, QuestionFactory.createCollection());
	}

	/**
	 * Construct and initialize a new <code>QuestionaireImpl</code>.
	 *
	 * The <code>author</code> property is set to the <code>NullIPerson</code>.
	 * 
	 * @param	<code>title</code> the questionaire's title.
	 * @param	<code>questions</code> the questionaire's questions.
	 * @throws	<code>IllegalArgumentException</code> if <code>title</code> is an
	 * 			empty string or if either argument is <code>null</code>.
	 */
	protected QuestionaireImpl(String title, IQuestionCollection questions)
		throws IllegalArgumentException {

		setTitle(title);
		setQuestions(questions);
		setAuthor(PersonFactory.getNullPerson());
	}

	/* ----------- Common Interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object's title
	 * is equivalent to the specified object's title.
	 *
	 * @param     <code>obj</code> the <code>Object</code> to compare with.
	 * @return    <code>true</code> if both object's questions are equal.
	 */
	public synchronized boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof IQuestionaire))
			return false;

		IQuestionaire other = (IQuestionaire) obj;

		if (!getTitle().equals(other.getTitle()))
			return false;
		if (!getAuthor().equals(other.getAuthor()))
			return false;

		IQuestionIterator ours = iterator();
		IQuestionIterator others = other.iterator();
		
		while (ours.hasNext()) {
			if (!others.hasNext())
				return false;
				
			if (!ours.next().equals(others.next()))
				return false;
		}
		
		if (others.hasNext())
			return false;		
			
        return true;
	}

	/**
	 * Generates a hash code for the receiver.
	 *
	 * This method is supported primarily for maps, such as those
	 * provided in <code>java.util.Map</code>.
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
		return getTitle() + " - " + getQuestions();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	////////////////////////////////////////////////////////////////////////////// 

	/* ----------- Mutators --------------------- */

	/**
	 * Set the questionaire's questions.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>questions</em>.
	 * 
	 * @param	<code>questions</code> the questionaire's questions.
	 * @throws	<code>IllegalArgumentException</code> if <code>id</code> is
	 *    		<code>null</code> or an empty string.
	 */
	protected void setQuestions(IQuestionCollection questions)
		throws IllegalArgumentException {

		if (null == questions)
			throw new IllegalArgumentException("questions null");

		IQuestionCollection oldQuestions = getQuestions();

		if (questions.equals(oldQuestions))
			return;

		fQuestions = questions;

		fQuestions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(evt);
			}
		});

		firePropertyChange("questions", oldQuestions, questions);
	}

	/* ----------- Accessors -------------------- */
			
	/**
	 * Answer the questionaire's questions.
	 *
	 * @return	the questions.
	 */
	protected IQuestionCollection getQuestions() {
		return fQuestions;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IQUESTIONAIRE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the questionaire's title.
	 * 
	 * Fires a <code>PropertyChangeEvent<code> called <em>title</em>.
	 *
	 * @param	<code>title</code> the questionaire's title.
	 * @throws	<code>IllegalArgumentException</code> if <code>title</code> is
	 *    		<code>null</code> or an empty string.
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		if (null == title)
			throw new IllegalArgumentException("title null");
		if ("".equals(title))
			throw new IllegalArgumentException("title empty string");

		String oldTitle = getTitle();

		fTitle = title;

		firePropertyChange("title", oldTitle, title);
	}

	/**
	 * Set the questionaire's author.
	 *
	 * Fires a <code>PropertyChangeEvent<code> called <em>author</em>.
	 * 
	 * @param	<code>author/code> the questionaire's author.
	 * @throws	<code>IllegalArgumentException</code> if <code>author</code> is
	 *    		<code>null</code>.
	 */
	public void setAuthor(IPerson author) throws IllegalArgumentException {
		if (null == author)
			throw new IllegalArgumentException("author null");

		IPerson oldAuthor = getAuthor();

		if (author.equals(oldAuthor))
			return;

		fAuthor = author;

		fAuthor.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(evt);
			}
		});

		firePropertyChange("author", oldAuthor, author);
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
	public void add(IQuestion question) throws IllegalArgumentException {
		getQuestions().add(question);
	}
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer the questionaire's title.
	 *
	 * @return	the title.
	 */
	public String getTitle() {
		return fTitle;
	}

	/**
	 * Answer the questionaire's author.
	 *
	 * @return	the author.
	 */
	public IPerson getAuthor() {
		return fAuthor;
	}

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
	public boolean contains(IQuestion question) {
		return getQuestions().contains(question);
	}

	/**
	 * Returns an iterator over the questions in this questionaire in proper
	 * sequence.
	 *
	 * @return	an <code>IQuestionIterator</code> over the questions in this
	 * 			collection in proper sequence.
	 * @see	de.sep.model.question.IQuestionIterator.
	 */
	public IQuestionIterator iterator() {
		return getQuestions().iterator();
	}

	/**
	 * Answer the number of questions this questionaire contains.
	 *
	 * @return	the number of questions.
	 */
	public int numberOfQuestions() {
		return getQuestions().size();
	}
		
	/**
	 * Returns <code>true</code> if this questionaire contains no questions.
	 *
	 * @return	<code>true</code> if this questionaire is empty.
	 */
	public boolean isEmpty() {
		return getQuestions().isEmpty();
	}

	/**
	 * Returns <code>true</code> if this questionaire contains at least one
	 * question.
	 *
	 * @return	<code>true</code> if this questionaire is not empty.
	 */
	public boolean isNotEmpty() {
		return getQuestions().isNotEmpty();
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT INULLABLE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer that we're not a <em>Null-object</em>.
	 *
	 * @return	<code>false</code>.
	 */
	public boolean isNull() {
		return false;
	}
}