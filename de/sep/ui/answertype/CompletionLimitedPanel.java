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

import java.util.ResourceBundle;

import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;

public class CompletionLimitedPanel extends AbstractAnswerTypePanel {


//////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
//////////////////////////////////////////////////////////////////////////////
public CompletionLimitedPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.COMPLETION_LIMITED, config);
	}
   



}