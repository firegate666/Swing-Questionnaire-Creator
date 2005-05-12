package de.sep.model.answertype;/*

/* SEP-Projekt WS 2001-2002 -- Questionaire
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

import de.sdavids.beans.IBoundBean;
import de.sdavids.interfaces.INullable;

/**
 * Read/write-interface for an answer type.
 * 
 * This interface supports the following simple bound properties:
 *
 * <ol>
 *    <li>type</li>
 * </ol>
 */
public interface IAnswerType extends IBoundBean, INullable {

    /* ----------- Accessors -------------------- */

    /**
     * Answer the answer type's type.
     *
     * @return	the type.
     */
    AnswerType getType();
}