package de.sep.model.answertype;

import de.sdavids.beans.BoundBean;

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
 * Base class for all <code>IAnswerType</code> implementations.
 */
public abstract class AbstractAnswerType
    extends BoundBean
    implements IAnswerType {

    //////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////////////////////////////////////////

    /**
     * No-argument constructor.
     */
    protected AbstractAnswerType() {
        super();
    }

    //////////////////////////////////////////////////////////////////////////////
    // PUBLIC INTERFACE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Common Interface ------------- */

    /**
     * Compares two objects for equality.
     *
     * Returns a boolean that indicates whether this object's questions
     * are equivalent to the specified object's questions.
     *
     * @param     <code>obj</code> the <code>Object</code> to compare with.
     * @return    <code>true</code> if both object's questions are equal.
     */
    public synchronized boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof IAnswerType))
            return false;

        IAnswerType other = (IAnswerType) obj;

        return (getType() == other.getType());
    }

    /**
     * Generates a hash code for the receiver.
     *
     * This method is supported primarily for hash tables, such as those
     * provided in <code>java.util.Hashtable</code>
     *
     * @return	an integer hash code for the receiver.
     * @see 	java.util.Hashtable.
     */
    public synchronized int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return	a string representation of the object.
     */
    public synchronized String toString() {
        return getType().getDescription();
    }

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT IANSWERTYPE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Answer the answer type's type.
     *
     * @return	the type.
     */
    abstract public AnswerType getType();

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT INULLABLE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Answer that we are not a <em>Null-object</em>.
     *
     * @return	<code>false</code>.
     * @see		de.sep.model.answertype.NullAnswerType.
     */
    public boolean isNull() {
        return false;
    }
}