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

import de.sdavids.exceptions.*;
import de.sep.model.questionaire.IQuestionaire;

/**
 * Interface for <code>IQuestionaire<code> storage/retrievals.
 */
public interface IServer {

    /* ----------- Mutators --------------------- */

    /**
     * Store the given questionaire.
     *
     * @param	<code>questionaire</code> the questionaire.
     * @throws	<code>InvalidPasswordException</code> if trouble with the
     *			server.
     */
    void store(IQuestionaire questionaire)
        throws ConnectionException;

    /* ----------- Accessors -------------------- */

    /**
     * Retrieve the questionaire with the given ID.
     *
     * @param	<code>id</code> the ID of the questionaire.
     * @return	the contact.
     * @throws	<code>InvalidPasswordException</code> if trouble with the
     * 			server.
     * @throws	<code>QuestionaireNotRegisteredException</code> if there's no
     * 			contact with the given ID stored in the database.
     */
    IQuestionaire retrieve(String id)
        throws ConnectionException, QuestionaireNotRegisteredException;
}