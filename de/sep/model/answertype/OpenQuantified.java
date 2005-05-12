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

public class OpenQuantified extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ---------- instance attributes --------------------- */

	/* minimum of the quantified answer */
	private int fMinValue;

	/* maximum of the quantified answer */
	private int fMaxValue;

	/* number of columns */
	private int fColumns;

	/* the text after the answer */
	private String fRightText;

	/* the text before the answer */
	private String fLeftText;

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.OPEN_QUANTIFIED</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.OPEN_QUANTIFIED;
	}

	/**
	 * Answer minimum Value of the accepted Number
	 *
	 * @return	the value.
	 */
	public int getMinValue() {
		return fMinValue;
	}

	/**
	 * Answer maximum Value of the accepted Number
	 *
	 * @return	the value.
	 */
	public int getMaxValue() {
		return fMaxValue;
	}

	/**
	 * Answer number of columns
	 *
	 * @return	the number.
	 */
	public int getColumns() {
		return fColumns;
	}
	/**
	 * Answer the string on the left side
	 * 
	 * @return	the string.
	 */
	public String getLeftText() {
		if (null == fLeftText)
			fLeftText = "";
		return fLeftText;
	}
	/**
	 * Answer the string on the right side 
	 *
	 * @return	the string.
	 */
	public String getRightText() {
		if (null == fRightText)
			fRightText = "";
		return fRightText;
	}

	/* ----------- Mutators -------------------- */

	/**
	 * set the maximum value of the quantified answer 
	 *
	 */
	public void setMaxValue(int maxValue) throws IllegalArgumentException {
		if (0 > maxValue)
			throw new IllegalArgumentException(maxValue + " < 0");

		int oldMaxValue = getMaxValue();

		if (oldMaxValue == maxValue)
			return;

		fMaxValue = maxValue;

		firePropertyChange("maxValue", oldMaxValue, maxValue);
	}
	/**
	* set the text after the quantified answer 
	*
	*/
	public void setRightText(String rightText) throws IllegalArgumentException {

		String oldRightText = getRightText();

		if (oldRightText.equalsIgnoreCase(rightText))
			return;

		fRightText = rightText;

		firePropertyChange("rightText", oldRightText, rightText);
	}

	/**
	* set the text before the quantified answer 
	*
	*/
	public void setLeftText(String leftText) throws IllegalArgumentException {

		String oldLeftText = getLeftText();

		if (oldLeftText.equalsIgnoreCase(leftText))
			return;

		fLeftText = leftText;

		firePropertyChange("leftText", oldLeftText, leftText);
	}

	/**
	* set the number of columns of quantified answer 
	*
	*/
	public void setColumns(int columns) throws IllegalArgumentException {
		if (0 > columns)
			throw new IllegalArgumentException(columns + " < 0");

		int oldColumns = getColumns();

		if (oldColumns == columns)
			return;

		fColumns = columns;

		firePropertyChange("columns", oldColumns, columns);
	}

	/**
	* set the minimum value of the quantified answer 
	*
	*/
	public void setMinValue(int minValue) throws IllegalArgumentException {
		if (0 > minValue)
			throw new IllegalArgumentException(minValue + " < 0");

		int oldMinValue = getMinValue();

		if (oldMinValue == minValue)
			return;

		fMinValue = minValue;

		firePropertyChange("minValue", oldMinValue, minValue);
	}

}