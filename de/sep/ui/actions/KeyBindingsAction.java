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
import de.sep.ui.dialog.KeyBindingsDialog;

/**
 * A <code>KeyBindingsAction<code> is invoked if the user wants to get informed on
 * the key bindings.
 * 
 * Invokes an <code>KeyBindingsDialog<code>.
 * 
 * @see		de.sep.ui.dialog.InfoDialog.
 */
public class KeyBindingsAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Non-modal dialog displaying the key bindings. */
	private KeyBindingsDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>KeyBindingsAction<code> object with the specified name and a
	 * reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public KeyBindingsAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog displaying the key bindings.
	 * 
	 * @return	the dialog.
	 */
	public KeyBindingsDialog getDialog() {
		if (null == fDlg) {
			fDlg = new KeyBindingsDialog(getFrame(), getConfig(), ((Questionaire) getApp()).getActions());

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
	 * Invoked when an <code>KeyBindingsAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(false);
		
		SwingUtils.showCentered(getFrame(), getDialog());
	}
}