package de.sep.logic;

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

import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.AnswerTypeFactory;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.question.IQuestion;
import de.sep.model.question.IQuestionIterator;
import de.sep.model.question.QuestionFactory;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.model.questionaire.QuestionaireFactory;

/**
 * Factory for all model classes.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 *
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class QuestionaireFactory2 {

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Null-object accessors --------------------- */

	/**
	 * Answer the <code>NullQuestionaire</code>.
	 *
	 * @see	de.sep.model.questionaire.NullQuestionaire.
	 */
	public static IQuestionaire getNullQuestionaire() {
		return QuestionaireFactory.getNullQuestionaire();
	}

	/**
	 * Answer the <code>NullQuestion</code>.
	 *
	 * @see	de.sep.model.question.NullQuestion.
	 */
	public static IQuestion getNullQuestion() {
		return QuestionFactory.getNullQuestion();
	}

	/**
	 * Answer the <code>NullAnswerType</code>.
	 *
	 * @see	de.sep.model.answertype.NullAnswerType.
	 */
	public static IAnswerType getNullAnswerType() {
		return AnswerTypeFactory.getNullAnswerType();
	}

	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Create a new titled <code>IQuestionaire</code>.
	 *
	 * @param	<code>title</code>the questionaire's title.
	 * @return	the questionaire.
	 */
	public IQuestionaire createQuestionaire(String title) {
		return QuestionaireFactory.create(title);
	}

	/**
	 * Create a new <code>IAnswerType</code> and append it to the given questionaire.
	 *
	 * @param	<code>questionaire</code> the questionaire.
	 * @return	the question.
	 * @throws	<code>IllegalArgumentException</code> if <code>questionaire</code>
	 *          is <code>null</code>.
	 */
	public IQuestion appendQuestion(IQuestionaire questionaire)
		throws IllegalArgumentException {

		return insertQuestion(questionaire, (getMaxNumber(questionaire) + 1));
	}

	/**
	 * Create a new <code>IQuestion</code> and insert it to the given questionaire.
	 *
	 * @param	<code>questionaire</code> the questionaire.
	 * @param	<code>number</code> the question's number.
	 * @return	the question.
	 * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
	 *          a positive number or if <code>questionaire</code> is <code>null</code>.
	 */
	public IQuestion insertQuestion(IQuestionaire questionaire, int number)
		throws IllegalArgumentException {

		if (questionaire.isNull())
			throw new IllegalArgumentException("questionaire null");

		IQuestion result = QuestionFactory.create(number);

		questionaire.add(result);

		return result;
	}

	/**
	 * Create a new <code>IAnswerType</code> of the given type and insert it at the given
	 * postion into the question's answer types.
	 *
	 * @param	<code>type</code> the answer type.
	 * @param	<code>question</code> the question.
	 * @param	<code>index</code> the postion.
	 * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
	 *          a positive number, <code>type</code> is not a valid answer type, or if
	 *          either <code>question</code> or <code>type</code> is <code>null</code>.
	 */
	public IAnswerType insertAnswerType(
		AnswerType type,
		IQuestion question,
		int index)
		throws IllegalArgumentException {

		if (question.isNull())
			throw new IllegalArgumentException("questionaire null");
		if (null == type)
			throw new IllegalArgumentException("type null");

		IAnswerType result = AnswerTypeFactory.create(type);

		question.add(result, index);

		return result;
	}

	/**
	 * Create a new <code>IAnswerType</code> of the given type and append it to the
	 * given question.
	 *
	 * @param	<code>question</code> the question.
	 * @param	<code>type</code> the answer type.
	 * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
	 *          a positive number, <code>type</code> is not a valid answer type, or if
	 *          either <code>question</code> or <code>type</code> is <code>null</code>.
	 */
	public IAnswerType appendAnswerType(AnswerType type, IQuestion question)
		throws IllegalArgumentException {

		if (question.isNull())
			throw new IllegalArgumentException("questionaire null");
		if (null == type)
			throw new IllegalArgumentException("type null");

		IAnswerType result = AnswerTypeFactory.create(type);

		question.add(result);

		return result;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Check whether the given title is a valid for a questionaire.
	 *
	 * @param	<code>title</code> the title.
	 */
	public static boolean isLegalQuestionaireTitle(String title) {
		return QuestionaireFactory.isLegalTitle(title);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Determine the the largest number assigned to an existing question.
	 * 
	 * @return	the number.
	 */
	protected int getMaxNumber(IQuestionaire questionaire) {
		int result = 0;
		int current = 0;

		IQuestionIterator it = questionaire.iterator();

		while (it.hasNext()) {
			current = it.next().getNumber();

			if (current > result)
				result = current;
		}

		return result;
	}
}