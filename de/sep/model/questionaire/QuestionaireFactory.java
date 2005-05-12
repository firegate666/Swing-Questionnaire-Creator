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

import de.sdavids.util.StringUtils;
import de.sep.model.question.QuestionFactory;

/**
 * Factory for <code>IQuestionaire</code>s.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 *
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class QuestionaireFactory {

	//////////////////////////////////////////////////////////////////////////////
	// INTITIALIZATION
	//////////////////////////////////////////////////////////////////////////////

	static {
		NullQuestionaire.init();
	}

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Factory Methods --------------------- */

	/**
	 * Create a new titled <code>IQuestionaire</code>.
	 *
	 * @param	<code>title</code>the questionaire's title.
	 * @return	the questionaire.
	 */
	public static IQuestionaire create(String title) {
		return new QuestionaireImpl(title);
	}
	
	/* ----------- Null-object accessors --------------------- */

	/**
	 * Answer the <code>NullQuestionaire</code>.
	 *
	 * @see	de.sep.model.questionaire.NullQuestionaire.
	 */
	public static IQuestionaire getNullQuestionaire() {
		return NullQuestionaire.instance();
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Check whether the given title is a valid for a questionaire.
	 *
	 * @param	<code>title</code> the title.
	 */
	public static boolean isLegalTitle(String title) {
		if ((null == title) || (title.length() < 1))
			return false;

		if (Character.isWhitespace(title.charAt(0)))
			return false;

		return StringUtils.hasOnlyLettersDigitsAndWS(title);
	}
}