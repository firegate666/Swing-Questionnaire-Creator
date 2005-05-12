package de.sep.model.question;

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

import de.sep.model.answertype.IAnswerTypeCollection;

/**
 * Use only for JUnit-testing!
 */
public class JUnitBridge {
	
	/**
	 * Use only for JUnit-testing!
	 */	
	public static QuestionCollectionImpl newQuestionCollectionImpl() {
		return new QuestionCollectionImpl();
	}
	
	/**
	 * Use only for JUnit-testing!
	 */		
	public static QuestionImpl newQuestionImpl() {
		return new QuestionImpl();
	}
	
	/**
	 * Use only for JUnit-testing!
	 */		
	public static QuestionImpl newQuestionImpl(int number) {
		return new QuestionImpl(number);
	}
	
	/**
	 * Use only for JUnit-testing!
	 */	
	public static QuestionImpl newQuestionImpl(int number, String text) {
		return new QuestionImpl(number, text);
	}	

	/**
	 * Use only for JUnit-testing!
	 */		
	public static QuestionImpl newQuestionaireImpl(int number, String text, IAnswerTypeCollection col) {
		return new QuestionImpl(number, text, col);
	}	
}
