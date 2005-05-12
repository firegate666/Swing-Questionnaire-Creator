package de.sep.model.answertype;

import java.awt.Component;

import javax.swing.JPanel;

import de.mb.util.StringList;
import de.sdavids.swing.controls.LabelTextFieldPanel;

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

public class OptionLimited extends Option {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* the list of the Options that can be choosen */
	private StringList fOptions;

	/* the number of Options which are allowed to choose */
	protected int fSelectableCount;

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.OPTION_LIMITED</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.OPTION_LIMITED;
	}
	/**
	 * Answer the number of options that can be choosen
	 *
	 * @return	 int 
	 */
	public int getSelectableCount() {
		return fSelectableCount;
	}
	/**
	 * Answer the list of options
	 *
	 * @return	stringList.
	 */
	public StringList getOptions() {
		if (null == fOptions) {
			fOptions = new StringList();

		}
		return fOptions;
	}
	/* ----------- Mutators -------------------- */
	/**
	 * Set the number of options that are selectable
	 */
	public void setSelectableCount(int count) {
		int oldCount = getSelectableCount();
		int newCount = count;
		fSelectableCount = count;
		firePropertyChange("count", oldCount, newCount);
	}

	/**
	 * Set the Options
	 *
	 */
	public void setOptions(Component[] components) {

		if (components.length == 0) {
			fOptions = new StringList();
			return;
		}
		getOptions().removeAll();
		for (int i = 0; i < components.length; i++) {
			String temp =
				(((LabelTextFieldPanel) (((JPanel) components[i]).getComponent(1))).getValue());

			getOptions().add(temp);

		}

	}

}