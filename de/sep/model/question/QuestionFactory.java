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

/**
 * Factory for <code>IQuestion</code>s and
 * <code>IQuestionCollection</code>s.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 * 
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class QuestionFactory {

	//////////////////////////////////////////////////////////////////////////////
	// INITIALIZATION
	//////////////////////////////////////////////////////////////////////////////

	static {
		NullQuestion.init();
	}

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Factory Methods --------------------- */

	/**
	 * Create a new <code>IQuestion</code> with the given number, an
	 * empty text and no answer types.
	 *
	 * Factory method.
	 * 
	 * @param	<code>number</code> the question's number.
	 * @param	<code>question</code> the question' text. 
	 * @throws <code>IllegalArgumentException</code> if <code>number</code> is not a 
	 * 			a positive number.
	 */
	public static IQuestion create(int number)
		throws IllegalArgumentException {
			
		return create(number, "");
	}
	
	/**
	 * Create a new <code>IQuestion</code> with the given number and text; it
	 * has no answer types.
	 *
	 * Factory method.
	 * 
	 * @param	<code>number</code> the question's number.
	 * @param	<code>text</code> the question' text. 
	 * @throws <code>IllegalArgumentException</code> if <code>number</code> is not a 
	 * 			a positive number of if <code>text</code> is <code>null</code>.
	 */
	public static IQuestion create(int number, String text)
		throws IllegalArgumentException {
			
		return new QuestionImpl(number, text);
	}

	/**
	 * Create an empty <code>IQuestionCollection</code>.
	 *
	 * Factory method.
	 *
	 * @return	a collection.
	 */
	public static IQuestionCollection createCollection() {
		return new QuestionCollectionImpl();
	}

	/* ----------- Null-object accessor --------------------- */

	/**
	 * Answer the <code>NullQuestion</code>.
	 * 
	 * @see	de.sep.model.question.NullQuestion.
	 */
	public static IQuestion getNullQuestion() {
		return NullQuestion.instance();
	}
}