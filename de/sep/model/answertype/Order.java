package de.sep.model.answertype;

import java.awt.Component;

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

public class Order extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* List of the Options that can be ordered */
	private StringList fOptions;

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IANSWERTYPE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.ORDER</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.ORDER;
	}

	/**
	* Answer the Lsit of Options that can be ordered
	*
	* @return	StringList
	*/
	public StringList getOptions() {
		if (null == fOptions) {
			fOptions = new StringList();

		}
		return fOptions;
	}

	/* ----------- Mutators -------------------- */

	/**
	 *	Set the Options
	 */
	public void setOptions(Component[] components) {

		if (components.length == 0) {
			fOptions = new StringList();
			return;
		}
		getOptions().removeAll();
		for (int i = 0; i < components.length; i++) {
			getOptions().add(((LabelTextFieldPanel) components[i]).getValue());
		}
		System.out.println("Options in model:"+fOptions.size());
	}

}