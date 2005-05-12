package de.sep.ui.dialog;

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

import de.sep.ui.Questionaire;

/**
 * A dialog for creating a new <code>Questionaire</code>.
 */
public class NewQuestionaireDialog extends RenameDialog {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a modal dialog with a reference to the application.
	 *
	 * @param	<code>app<code> a reference to the application.
	 */
	public NewQuestionaireDialog(Questionaire app) {
		super(app);

	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
		
	/**
	 * Invoked when the title property of the current document changes.
	 */
	protected void updateTitleAction() {
		setTitle(getResource("dlg.newQuestionaire.title"));
	}	

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */
		
	/**
	 * Initialize the dialog.
	 * 
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {	
		super.initialize();
		
		setModal(true);
	}
}