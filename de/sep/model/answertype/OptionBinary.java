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

public class OptionBinary extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* the string of the upperlabel */
	protected String fUpperLabel;

	/* the string of lowerlabel */
	protected String fLowerLabel;

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.OPTION_BINARY</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.OPTION_BINARY;
	}
	/**
	 * Answer the string of the upperlabel
	 *
	 * @return	the string.
	 */
	public String getUpperLabel() {
		return fUpperLabel;
	}
	/**
	 * Answer the string of the lowerlabel
	 *
	 * @return	the string.
	 */
	public String getLowerLabel() {
		return fLowerLabel;
	}

	/* ----------- Mutators -------------------- */

	/**
	 * Set the String of the upperLabel
	 *
	 */
	public void setUpperLabel(String upperLabel) throws IllegalArgumentException {

		String oldUpperLabel = getUpperLabel();

		if (oldUpperLabel == upperLabel)
			return;

		fUpperLabel = upperLabel;

		firePropertyChange("upperLabel", oldUpperLabel, upperLabel);
	}

	/**
	 * Set the String of the lowerLabel
	 *
	 */
	public void setLowerLabel(String lowerLabel) throws IllegalArgumentException {

		String oldLowerLabel = getLowerLabel();

		if (oldLowerLabel == lowerLabel)
			return;

		fLowerLabel = lowerLabel;

		firePropertyChange("lowerLabel", oldLowerLabel, lowerLabel);
	}

}