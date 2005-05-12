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

import de.sdavids.exceptions.ChainedException;

/**
 * A <code>InvalidPasswordException</code> is thrown if a connection error with a
 * server occurred, i.e. could not connect or disconnect to the service, or
 * other trouble.
 */
public class IllegalAnswerTypeException extends ChainedException {

    /**
     * Constructs a <code>InvalidPasswordException</code> with <code>null</code>
     * as its error message string.
     */
    public IllegalAnswerTypeException() {
        super();
    }

    /**
     * Constructs a <code>InvalidPasswordException</code>, saving a reference
     * to the error message string for later retrieval by the
     * <code>getMessage</code> method.
     *
     * @param	<code>message</code> the detail message.
     */
    public IllegalAnswerTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>InvalidPasswordException</code>, saving a reference
     * to the error message string for later retrieval by the
     * <code>getMessage</code> method.
     *
     * @param	<code>message</code> the detail message.
     * @param	<code>cause</code> the cause.
     */
    public IllegalAnswerTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}