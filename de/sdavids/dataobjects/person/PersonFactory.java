package de.sdavids.dataobjects.person;

import de.sdavids.dataobjects.common.*;

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
 * Factory for <code>Person</code>s.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 * 
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class PersonFactory {

    //////////////////////////////////////////////////////////////////////////////
    // INITIALIZATION
    //////////////////////////////////////////////////////////////////////////////

    static {
        NullPerson.init();
    }

    //////////////////////////////////////////////////////////////////////////////
    // CLASS METHODS
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Factory Methods --------------------- */

    /**
     * Create a new <code>Person</code> with the given first and last name.
     *
     * Factory method.
     * 
     * @param	<code>firstName</code> the person's first name.
     * @param	<code>lastName</code> the person's last name.
     */
    public static IPerson create(String firstName, String lastName) {
        return new Person(firstName, lastName);
    }

    /**
     * Create a new <code>Person</code> with the given first, middle, and last
     * name.
     *
     * Factory method.
     * 
     * @param	<code>firstName</code> the person's first name.
     * @param	<code>lastName</code> the person's last name.
     * @param	<code>middleName</code> the person's middle name.
     */
    public static IPerson create(String firstName, String lastName, String middleName) {
        return new Person(firstName, lastName, middleName);
    }
    
    /* ----------- Null-object accessor --------------------- */

    /**
     * Answer the <code>NullQuestion</code>.
     * 
     * @see		de.sep.model.question.NullQuestion.
     */
    public static IPerson getNullPerson() {
        return NullPerson.instance();
    }
}