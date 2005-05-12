package de.sep.dnd;

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

import java.awt.Color;
import java.awt.Component;

import de.sep.dnd.*;

import javax.swing.UIManager;

import de.sep.ui.question.*;

/**
 * The <code>QuestionEndDropPanel</code> is similar to a
 * <code>QuestionDropPanel</code> but appends questions to the end of the
 * questionaire.
 * 
 * It's main use is to make it possible to drag questions to the end of the panel.
 * 
 * If a <code>QuestionTransferable</code> is dropped over a
 * <code>QuestionEndDropPanel</code> a new question is appended to the end of the
 * questionaire.
 */
public class QuestionEndDropPanel extends QuestionDropPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The original background color of the parent panel. */
	private Color fOriginalParentColor;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////    

	/**
	 * Create a new <code>QuestionEndDropPanel</code> with the default
	 * &quot;highlight&quot; color orange.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public QuestionEndDropPanel() {
		this(Color.orange);
	}

	/**
	 * Create a new <code>QuestionEndDropPanel</code> with the given
	 * &quot;highlight&quot; color.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public QuestionEndDropPanel(Color highlight) {
		super(highlight);
		
		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Highlight the drop panel.
	 */
	public void showSelected() {
		super.showSelected();

		Component parent = getParent();
		
		if (null != parent)
			setOriginalParentBackground(getParent().getBackground());
		
		getParent().setBackground(getHighlight());
	}

	/**
	 * Remove the highlight from the drop panel.
	 */
	public void showUnselected() {
		super.showUnselected();
		
		Component parent = getParent();
		
		if (null != parent)
			parent.setBackground(getOriginalParentBackground());
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the panel.
	 * 
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		super.initialize();
	}	
		
	/**
	 * Intiate the update of the model.
	 * 
	 * Unselects the panel and fires a <code>PropertyChange</code> event called
	 * <em>append</em>; both the new and old value of the event should not be
	 * used, they are meaningless.
	 */
	protected void dropIt() {
		showUnselected();
		
		firePropertyChange("appendQuestion", -1, 0);
	}
	
	/* ----------- Accessors --------------------- */
	
	/**
	 * Get the parent's original background color.
	 */
	protected Color getOriginalParentBackground() {
		return fOriginalParentColor;
	}
	
	/**
	 * Set the original parent's background color.
	 * 
	 * @param	<code>origColor</code> the color.
	 */
	public void setOriginalParentBackground(Color origColor) {
		fOriginalParentColor = origColor;
	}		
}