package de.sep.ui.answertype;

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

import java.awt.Color;
import java.util.ResourceBundle;

import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;

public class ScaleLabledPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////
	public ScaleLabledPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.SCALE_LABLED, config);
		setColor(Color.yellow);
	}

}