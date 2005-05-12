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

public class ScaleCalculated extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/* the number of the left pol */
	private int fLeft;

	/* the number of the left pol */
	private int fRight;

	/* the average of the left and the right pol */
	private int fAvg;

	/* the text of the left pol */
	private String fLeftTxt;

	/* the text of the right pol */
	private String fRightTxt;

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.SCALE_CALCULATED</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.SCALE_CALCULATED;
	}

	/**
	 * Answer the text of the right text
	 *
	 * @return	String.
	 */
	public String getRightLable() {
		if (null == fRightTxt) {
			fRightTxt = new String();
		}
		return fRightTxt;
	}

	/**
	 * Answer the text of the left text
	 *
	 * @return	String.
	 */
	public String getTextBefore() {
		if (null == fLeftTxt) {
			fLeftTxt = new String();
		}
		return fLeftTxt;
	}

	/**
	 * Answer the number of the left pol
	 *
	 * @return	int.
	 */
	public int getLeft() {
		return fLeft;
	}

	/**
	 * Answer the number of the right pol
	 *
	 * @return	int.
	 */
	public int getRight() {
		return fRight;
	}

	/**
	 * Answer average of the left and right pol
	 *
	 * @return	int.
	 */
	public int getAvg() {
		return fAvg;
	}

	/* ----------- Mutators -------------------- */

	/**
	 * set the text of the left Pol
	 */
	public void setLeftText(String s) {
		String oldLeftTxt = getTextBefore();
		String leftTxt = s;
		fLeftTxt = s;
		firePropertyChange("lefttxt", oldLeftTxt, leftTxt);
	}

	/**
	 * set the text of the right Pol
	 */
	public void setRightText(String s) {
		String oldRightTxt = getTextBefore();
		String rightTxt = s;
		fRightTxt = s;
		firePropertyChange("righttxt", oldRightTxt, rightTxt);
	}

	/**
	 * set the number of the left Pol
	 */
	public void setLeft(int left) {
		int oldLeft = getLeft();
		int newLeft = left;
		fLeft = left;
		firePropertyChange("leftnumber", oldLeft, newLeft);
	}

	/**
	 * set the number of the right Pol
	 */
	public void setRight(int right) {
		int oldRight = getRight();
		int newRight = right;
		fRight = right;
		firePropertyChange("rightnumber", oldRight, newRight);
	}

	/**
	 * set average of the left and right pol
	 */
	public void setAvg(int avg) {
		int oldAvg = getAvg();
		int newAvg = avg;
		fAvg = avg;
		firePropertyChange("avgnumber", oldAvg, newAvg);
	}

}