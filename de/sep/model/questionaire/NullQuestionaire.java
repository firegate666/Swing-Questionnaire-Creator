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

import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.dataobjects.person.PersonFactory;
import de.sdavids.exceptions.SingletonException;
import de.sep.model.question.IQuestionCollection;
import de.sep.model.question.QuestionFactory;

/**
 * <code>Questionaire</code> <em>Null-object</em>.
 * 
 * Singleton.
 *
 * Call <code>init()</code> before first use of singleton to initilize it.
 *
 * Utilizes the <code>Singleton</code> pattern.
 * 
 * Reference:<br>
 * <dl>
 * <dt>Singleton<dt>
 * <dd>Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.</dd>
 * <dt>Introduce Null Object<dt>
 * <dd>http://www.refactoring.com/catalog/introduceNullObject.html</dd>
 * </dl>
 */
class NullQuestionaire extends QuestionaireImpl {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ---------- */

	/** The singleton. */
	static private NullQuestionaire INSTANCE;

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Singleton Initializer ---------- */

	/**
	 * Initialize the singleton instance.
	 *
	 * This method that has to be called once, before one calls
	 * <code>instance()</code>.
	 */
	public static synchronized void init() {
		if (null == INSTANCE)
			INSTANCE = new NullQuestionaire();
	}

	/* ----------- Singleton Accessor ---------- */

	/**
	 * Returns a reference to the singleton instance.
	 *
	 * @return	the reference of the singleton instance.
	 * @throws	<code>SingletonException<code> if <code>instance()</code>
	 * 			is called on not initialized singleton (via
	 * 			<code>init()</code>).
	 * @see	#init
	 */
	public static NullQuestionaire instance() throws SingletonException {
		if (null == INSTANCE)
			throw new SingletonException();

		return INSTANCE;
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/** 
	 * Prevent instantiantion.
	 */
	protected NullQuestionaire() {
		super();
		super.setQuestions(QuestionFactory.createCollection());
		super.setAuthor(PersonFactory.getNullPerson());		
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Common Interface ------------- */

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object is the same as the
	 * specified object.
	 *
	 * @param     <code>obj</code> the <code>Object</code> to compare with.
	 * @return    <code>true</code> if both object's questions are equal.
	 */
	public synchronized boolean equals(Object obj) {
		return (this == obj);
	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return	an empty string.
	 */
	public synchronized String toString() {
		return "";
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IQUESTIONAIRE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * No change in object regardless of parameter.
	 */
	public void setAuthor(IPerson author) {}
	
	/**
	 * No change in object regardless of parameter.
	 */
	public void setTitle(String title) {}

	/**
	 * No change in object regardless of parameter.
	 */
	public void setQuestions(IQuestionCollection questions) {}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer an empty string.
	 *
	 * @return	an empty string.
	 */
	public String getTitle() {
		return "";
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT INULLABLE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer that we are a <em>Null-object</em>.
	 *
	 * @return	<code>true</code>.
	 */
	public boolean isNull() {
		return true;
	}
}