package de.sep.model.answertype;

import de.mb.util.StringList;

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

public class ScaleLabled extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.SCALE_LABLED</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.SCALE_LABLED;
	}
	public String getLeftLable() {
		String result = new String();

		return result;
	}
	public String getRightLable() {
		String result = new String();

		return result;
	}
	public String getTextBefore() {
		String result = new String();

		return result;
	}
	public StringList getScaleDescriptions() {
		StringList result = new StringList();

		return result;
	}

}