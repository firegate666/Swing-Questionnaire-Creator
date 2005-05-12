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

public class OptionVariable extends Option {

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.OPTION_VARIABLE</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.OPTION_VARIABLE;
	}

}