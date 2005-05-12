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

import java.util.ResourceBundle;

import de.sdavids.beans.BoundBean;
import de.sep.model.answertype.AnswerTypeFactory;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IAnswerTypeCollection;
import de.sep.model.answertype.IAnswerTypeIterator;

/**
 * Implementation of the <code>IAnswerType</code> interface.
 * 
 * JavaBean.
 * 
 * Has the following simple bound properties (readable & writeable):
 *
 * <ol>
 *    <li>number</li>
 *    <li>text</li>
 *    <li>answers</li>
 * </ol>
 */
class QuestionImpl extends BoundBean implements IQuestion {

    //////////////////////////////////////////////////////////////////////////////
    // ATTRIBUTES
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Instance Attributes ---------- */

    /** The question's number.
     * 
     * R/W bound property.
     */
    private int fNumber;

    /** The question's question text.
     * 
     * R/W bound property.
     */
    private String fText;

    /** The question's answers.
     * 
     * R/W bound property.
     */
    private IAnswerTypeCollection fAnswers;

    //////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////////////////////////////////////////

    /**
     * No-argument constructor.
     * 
     * <code>getNumber</code> will return <code>Integer.MAX_VALUE</code>.
     * <code>getText</code> will return an empty String.
     * <code>getNumber</code> will return an empty
     * <code>IAnswerTypeCollection</code>.
     */
    public QuestionImpl() {
    	setNumber(Integer.MAX_VALUE);
    	setText("");
    	setAnswers(AnswerTypeFactory.createCollection());
    }

    /**
     * Construct and initialize a new <code>QuestionImpl</code>.
     * 
     * The <code>text</code> property is set to an empty string.
     *
     * @param	<code>number</code> the question's number.
     * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
     *         	a positive number.
     */
    public QuestionImpl(int number) throws IllegalArgumentException {
        this(number, "");
    }

    /**
     * Construct and initialize a new <code>QuestionImpl</code>.
     *
     * @param	<code>number</code> the question's number.
     * @param	<code>text</code> the question' text.
     * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
     *         	positive number or if <code>text</code> is <code>null</code>.
     */
    public QuestionImpl(int number, String text)
        throws IllegalArgumentException {

        this(number, text, AnswerTypeFactory.createCollection());
    }

    /**
     * Construct and initialize a new <code>QuestionImpl</code>.
     *
     * @param	<code>number</code> the question's number.
     * @param	<code>text</code> the question' text.
     * @param	<code>answers</code> the question' answers.
     * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
     *         	positive number or if <code>text</code> or 
     *          <code>answers</code> is <code>null</code>.
     */
    public QuestionImpl(
        int number,
        String text,
        IAnswerTypeCollection answers)
        throws IllegalArgumentException {

        setNumber(number);
        setText(text);
        setAnswers(answers);
    }

    //////////////////////////////////////////////////////////////////////////////
    // PUBLIC INTERFACE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */

    /**
     * Adds the specified answer to the end of the end of this question.
     *
     * Fires <code>PropertyChangeEvent<code>s whenever a answer is added.
     *
     * @param	<code>answerType</code> the answer to be added.
     * @throws	<code>IllegalArgumentException</code> if <code>answer</code>
     *        	is <code>null</code>.
     */
	public synchronized void add(IAnswerType answerType) 
		throws IllegalArgumentException {
			
		getAnswers().add(answerType);
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
			
		getAnswers().add(answerType, index);
	}
			
    /* ----------- Common Interface ------------- */

    /**
     * Compares two objects for equality.
     *
     * Returns a boolean that indicates whether this object's number and text
     * are equivalent to the specified object's number and text.
     * 
     * Also, both objects have to have the same number of answer types in the
     * same order.
     *
     * @param	<code>obj</code> the <code>Object</code> to compare with.
     * @return	<code>true</code> if the conditions above are met.
     */
    public synchronized boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof IQuestion))
            return false;

        IQuestion other = (IQuestion) obj;

        if (getNumber() != other.getNumber())
            return false;
        if (!getText().equals(other.getText()))
            return false;
	
		IAnswerTypeIterator ours = iterator();
		IAnswerTypeIterator others = other.iterator();
		
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
        return getNumber() + " " + getText();
    }

    //////////////////////////////////////////////////////////////////////////////
    // PROTECTED METHODS
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */
    
    /**
     * Set the question's answers.
     *
	   * Fires a <code>PropertyChangeEvent<code> called <em>answers</em>.
     *
     * @param	<code>answers</code> the question' answers.
     * @throws	<code>IllegalArgumentException</code> if <code>answers</code> is
     *        	<code>null</code>.
     */
    protected void setAnswers(IAnswerTypeCollection answers)
        throws IllegalArgumentException {

        if (null == answers)
            throw new IllegalArgumentException("answers null");

        IAnswerTypeCollection oldAnswers = getAnswers();

		if (answers.equals(oldAnswers))
			return;
			
        fAnswers = answers;

        fAnswers.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                firePropertyChange(evt);
            }
        });

        firePropertyChange("answers", oldAnswers, answers);
    }

    /* ----------- Accessors -------------------- */
        
    /**
     * Answer the question's answers.
     *
     * @return	the answers.
     */
    protected IAnswerTypeCollection getAnswers() {
        return fAnswers;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT IQUESTION
    //////////////////////////////////////////////////////////////////////////////

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
    public void setNumber(int number) throws IllegalArgumentException {
        if (0 > number)
            throw new IllegalArgumentException(number + " < 0");

		int oldNumber = getNumber();
			
        fNumber = number;

        firePropertyChange("number", oldNumber, number);
    }

    /**
     * Set the question's question text.
     *
	 * Fires a <code>PropertyChangeEvent<code> called <em>text</em>.
     *
     * @param	<code>question</code> the question' text.
     * @throws	<code>IllegalArgumentException</code> if <code>question</code> is
     *        	<code>null</code>.
     */
    public void setText(String question) throws IllegalArgumentException {
        if (null == question)
            throw new IllegalArgumentException("question null");

        String oldQuestion = getText();
        
        fText = question;

        firePropertyChange("text", oldQuestion, question);
    }

    /**
     * Compares two questions.
     * 
     * Compares the two questions numbers numerically.
     *
     * @param   <code>question</code>   the question to be compared.
     * @return  the value <code>0</code> if the argument question is equal to
     *          this question; a value less than <code>0</code> if this
     *          question smaller than the question argument; and a
     *          value greater than <code>0</code> if this question is
     *          numerically greater than the question argument
     *			(signed comparison).
     */
    public int compareTo(IQuestion question) {
        int thisNum = getNumber();
        int anotherNum = question.getNumber();

        return ((thisNum < anotherNum) ? -1 : (thisNum == anotherNum ? 0 : 1));
    }

    /* ----------- Accessors -------------------- */

    /**
     * Answer the question's number.
     *
     * @return	the number.
     */
    public int getNumber() {
        return fNumber;
    }

    /**
     * Answer the question's question.
     *
     * @return	the question.
     */
    public String getText() {
        return fText;
    }

	/**
	 * Returns <code>true</code> if this collection contains at least one
	 * answerType.
	 *
	 * @return	<code>true</code> if this collection is not empty.
	 */
	public boolean hasAnswerTypes() {
		return getAnswers().isNotEmpty();
	}

	/**
	 * Returns <code>true</code> if this question contains no answer types.
	 *
	 * @return	<code>true</code> if this collection is empty.
	 */
	public boolean hasNoAnswerTypes() {
		return getAnswers().isEmpty();
	}

	/**
	 * Returns an iterator over the answer types in this question in proper
	 * sequence.
	 *
	 * @return	an <code>IAnswerTypeIterator</code> over the answer types in
	 * 			this question in proper sequence.
	 * @see	de.sep.model.answertype.IAnswerTypeIterator.
	 */
	public IAnswerTypeIterator iterator() {
		return getAnswers().iterator();
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

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT COMPARABLE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Compares this question to another <code>Object</code>.
     * 
     * If the object is an <code>IQuestion</code>, this method behaves like
     * <code>compareTo(IQuestion)</code>.  Otherwise, it throws a 
     * <code>ClassCastException</code> (as <code>IQuestion</code>s are
     * comparable only to other <code>IQuestion</code>s).
     *
     * @param	<code>obj</code>   the <code>Object</code> to be compared.
     * @return	the value <code>0</code> if the argument question is equal to
     *          this question; a value less than <code>0</code> if this
     *          question smaller than the question argument; and a
     *          value greater than <code>0</code> if this question is
     *          numerically greater than the question argument
     *			(signed comparison).
     */
    public int compareTo(Object obj) {
        return compareTo((IQuestion) obj);
    }
}