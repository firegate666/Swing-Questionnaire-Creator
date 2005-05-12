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
import de.sep.ui.Questionaire;
import de.sep.ui.dialog.InfoDialog;

/**
 * A <code>InfoAction<code> is invoked if the user wants to gain information
 * about the application.
 * 
 * Invokes an <code>InfoDialog<code>.
 * 
 * @see		de.sep.ui.dialog.InfoDialog.
 */
public class InfoAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Non-modal dialog displaying information on the application. */
	private InfoDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>InfoAction<code> object with the specified name and a
	 * reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public InfoAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog displaying information on the application.
	 * 
	 * @return	the dialog.
	 */
	protected InfoDialog getDialog() {
		if (null == fDlg) {
			fDlg = new InfoDialog(getFrame(), getConfig());

			fDlg.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					setEnabled(true);
				}				
				
				public void windowClosed(WindowEvent evt) {
					setEnabled(true);
				}
			});
		}

		return fDlg;
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>InfoAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(false);

		SwingUtils.showCentered(getFrame(), getDialog());
	}
}