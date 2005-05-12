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

import java.util.NoSuchElementException;

/**
 * An iteration over an <code>IAnswerCollection</code>.
 *
 * @see		de.sep.model.questionaire.answer.IAnswerCollection
 */
public interface IAnswerTypeIterator {

    /* ----------- Mutators --------------------- */

    /**
     * Returns the next answer in the iteration.
     *
     * @return	the next question in the iteration.
     * @throws	<code>NoSuchElementException</code> iteration has no
     *        	more answers.
     */
    IAnswerType next() throws NoSuchElementException;

    /* ----------- Accessors -------------------- */

    /**
     * Returns <code>true</code> if the iteration has more answers.
     *
     * In other words, returns <code>true</code> if <code>next</code>
     * would return an answer rather than throwing an exception.
     *
     * @return	<code>true</code> if the iterator has more answers.
     */
    boolean hasNext();
}