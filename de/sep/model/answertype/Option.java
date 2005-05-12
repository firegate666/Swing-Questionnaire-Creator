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

public abstract class Option extends AbstractAnswerType {

	protected StringList fOptions;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	//////////////////////////////////////////////////////////////////////////////

	public Option() {
		fOptions = new StringList();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	//////////////////////////////////////////////////////////////////////////////
	/* ----------- Mutators -------------------- */
	public void setOptions(StringList options) throws IllegalArgumentException {
		StringList oldOptions = getOptions();

		if (oldOptions.equals(options))
			return;

		fOptions = options;

		firePropertyChange("options", oldOptions, options);
	}

	/* ----------- Accessors -------------------- */

	public StringList getOptions() {
		return fOptions;
	}

}