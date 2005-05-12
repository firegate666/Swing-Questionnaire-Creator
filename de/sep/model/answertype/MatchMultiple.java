package de.sep.model.answertype;

import de.mb.util.StringList;

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

/**
 */
public class MatchMultiple extends AbstractAnswerType {

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT IANSWERTYPE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Answer <code>AnswerType.MATCH_MULTIPLE</code>.
     *
     * @return	the type.
     */
    public AnswerType getType() {
        return AnswerType.MATCH_MULTIPLE;
    }
    public StringList getRowOptions() {
        StringList result = new StringList();

        return result;
    }
    public StringList getColumnOptions() {
        StringList result = new StringList();

        return result;
    }

}