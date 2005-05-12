package de.sep.model.questionaire;

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

import de.sep.model.question.IQuestionCollection;

/**
 * Use only for JUnit-testing!
 */
public class JUnitBridge {
	public static QuestionaireImpl newQuestionaireImpl() {
		return new QuestionaireImpl();
	}
	
	public static QuestionaireImpl newQuestionaireImpl(String title) {
		return new QuestionaireImpl(title);
	}
	
	public static QuestionaireImpl newQuestionaireImpl(String title, IQuestionCollection col) {
		return new QuestionaireImpl(title, col);
	}	
}
