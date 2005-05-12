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

import de.sdavids.exceptions.ChainedException;

/**
 * A <code>QuestionaireNotRegisteredException</code> is thrown if an attempt is
 * made to retrieve a questionaire not registered with the server.
 */
public class QuestionaireNotRegisteredException extends ChainedException {

    /**
     * Constructs a <code>QuestionaireNotRegisteredException</code> with
     * <code>null</code> as its error message string.
     */
    public QuestionaireNotRegisteredException() {
        super();
    }

    /**
     * Constructs a <code>QuestionaireNotRegisteredException</code>, saving a
     * reference to the error message string for later retrieval by the
     * <code>getMessage</code> method.
     *
     * @param	<code>s</code> the detail message.
     */
    public QuestionaireNotRegisteredException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>QuestionaireNotRegisteredException</code>, saving a
     * reference to the error message string for later retrieval by the
     * <code>getMessage</code> method.
     *
     * @param	<code>message</code> the detail message.
     * @param	<code>cause</code> the cause.
     */
    public QuestionaireNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}