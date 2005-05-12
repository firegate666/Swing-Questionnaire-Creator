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

public class OpenQualified extends AbstractAnswerType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */
	
	/* number of rows of the textarea  */
	private int fRows;
	
	/* number of columns of the textarea  */
	private int fColumns;

	/* ----------- Accessors -------------------- */

	/**
	 * Answer <code>AnswerType.OPEN_QUALIFIED</code>.
	 *
	 * @return	the type.
	 */
	public AnswerType getType() {
		return AnswerType.OPEN_QUALIFIED;
	}

	/**
	 * Answer the number of rows that this <code>AnswerType</code> has
	 *
	 * @return	number of rows
	 */
	public int getRows() {
		return fRows;
	}

	/**
	 * Answer the number of columns that this <code>AnswerType</code> has
	 *
	 * @return	number of columns
	 */
	public int getColumns() {
		return fColumns;
	}

	/* ----------- Mutators -------------------- */

    /**
     * Set the number of rows
     *
     * @param	<code>number</code> the number.
     * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
     *        	positive number.
     */
    public void setRows(int rows) throws IllegalArgumentException {
        if (0 > rows)
            throw new IllegalArgumentException(rows + " < 0");

        int oldRows = getRows();

		if (oldRows == rows) return;
		
        fRows = rows;

        firePropertyChange("rows", oldRows, rows);
    }
    
     /**
     * Set the number of columns
     *
     * @param	<code>number</code> the number.
     * @throws	<code>IllegalArgumentException</code> if <code>number</code> is not a
     *        	positive number.
     */
    public void setColumns(int columns) throws IllegalArgumentException {
        if (0 > columns)
            throw new IllegalArgumentException(columns + " < 0");

        int oldColumns = getColumns();

		if (oldColumns == columns) return;
		
        fColumns = columns;

        firePropertyChange("columns", oldColumns, columns);
    }
	
}