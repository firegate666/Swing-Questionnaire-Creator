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

/**
 * Factory for <code>IAnswerType</code>s and
 * <code>IAnswerTypeCollection</code>s.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 * 
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class AnswerTypeFactory {

	//////////////////////////////////////////////////////////////////////////////
	// INITIALIZATION
	//////////////////////////////////////////////////////////////////////////////

	static {
		NullAnswerType.init();
	}

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS

	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Factory Methods --------------------- */

	/**
	 * Create a new <code>IAnswerType</code> of the given type.
	 *
	 * Factory method.
	 * 
	 * @param	<code>type</code> the type.
	 */
	public static IAnswerType create(AnswerType type)
		throws IllegalArgumentException {

		int value = type.getValue();

		if (AnswerType.COMPLETION_LIMITED.getValue() == value) {
			return new CompletionLimited();
		} else if (AnswerType.COMPLETION_OPEN.getValue() == value) {
			return new CompletionOpen();
		} else if (AnswerType.MATCH_MULTIPLE.getValue() == value) {
			return new MatchMultiple();
		} else if (AnswerType.MATCH_SINGLE.getValue() == value) {
			return new MatchSingle();
		} else if (AnswerType.OPEN_QUALIFIED.getValue() == value) {
			return new OpenQualified();
		} else if (AnswerType.OPEN_QUANTIFIED.getValue() == value) {
			return new OpenQuantified();
		} else if (AnswerType.OPTION_BINARY.getValue() == value) {
			return new OptionBinary();
		} else if (AnswerType.OPTION_LIMITED.getValue() == value) {
			return new OptionLimited();
		} else if (AnswerType.OPTION_VARIABLE.getValue() == value) {
			return new OptionVariable();
		} else if (AnswerType.ORDER.getValue() == value) {
			return new Order();
		} else if (AnswerType.SCALE_CALCULATED.getValue() == value) {
			return new ScaleCalculated();
		} else if (AnswerType.SCALE_LABLED.getValue() == value) {
			return new ScaleLabled();
		} else {
			throw new IllegalArgumentException("unknown type: " + type);
		}
	}

	/**
	 * Create an empty <code>IAnswerTypeCollection</code>.
	 *
	 * Factory method.
	 *
	 * @return    a collection.
	 */
	public static IAnswerTypeCollection createCollection() {
		return new AnswerTypeCollectionImpl();
	}

	/* ----------- Null-object accessor --------------------- */

	/**
	 * Answer the <code>NullAnswerType</code>.
	 *
	 * @see	de.sep.model.answertype.NullAnswerType.
	 */
	public static IAnswerType getNullAnswerType() {
		return NullAnswerType.instance();
	}
}