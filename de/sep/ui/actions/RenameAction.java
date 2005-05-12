package de.sep.ui.actions;

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

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import de.sdavids.swing.SwingUtils;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;
import de.sep.ui.dialog.RenameDialog;

/**
 * A <code>RenameAction<code> is invoked if the user wants to rename an existing
 * questionaire.
 */
public class RenameAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Non-modal dialog for entering the questionaire's name. */
	private RenameDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>RenameAction<code> object with the specified name and a
	 * reference to the application.
	 * 
	 * @param	<code>app<code> a reference to the application. 
	 * @param	<code>name<code> the name
	 */
	public RenameAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Renames a questionaire.
	 */
	protected void execute() {
		IQuestionaire doc = ((Questionaire) getApp()).getCurrentDoc();
		String docTitle = doc.getTitle();
		String title = getDialog().getQuestionaireTitle();

		setEnabled(true);

		if ((docTitle == title) || (docTitle.equals(title)))
			return;

		performAction(ACTION_SAVE, doc, doc.hashCode(), "rename");
		//non-I18N-NLS

		doc.setTitle(title);
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog for renaming a questionaire.
	 * 
	 * @return	the dialog.
	 */
	public RenameDialog getDialog() {
		if (null == fDlg) {
			fDlg = new RenameDialog(((Questionaire) getApp()));

			fDlg.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					execute();
				}
			});
		}

		return fDlg;
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>RenameAction<code> occurs.
	 * 
	 * Displays a non-modal dialog.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(false);

		SwingUtils.showCentered(getFrame(), getDialog());
	}
}