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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.SwingUtils;
import de.sdavids.swing.dialog.ButtonConstants;
import de.sdavids.swing.dialog.ConfirmationDialog;
import de.sep.ui.Questionaire;

/**
 * A <code>AbandonChangesAction<code> is invoked if the user wants to abondon all
 * changes he's done to the current document.
 */
public class AbandonChangesAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Modal dialog queries whether the changes should really be abandoned. */
	private ConfirmationDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>AbandonChangesAction<code> object with the specified
	 * name and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application.
	 */
	public AbandonChangesAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog which queries whether the changes should really be
	 * abandoned.
	 * 
	 * @return	the dialog.
	 */
	public ConfirmationDialog getDialog() {
		if (null == fDlg) {
			fDlg = new ConfirmationDialog(getFrame(), getConfig(), "dlg.abandonChanges");

			String[] values = { ((Questionaire) getApp()).getCurrentDoc().getTitle() };
			
			String title = fDlg.getTitle();
						
			title = SwingCreator.replacePlaceHolders(title, values);
						
			fDlg.setTitle(title);
						
			((Questionaire) getApp()).getCurrentDoc().addPropertyChangeListener("title", new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					String[] values = { (String) evt.getNewValue() };
					String title = getConfig("dlg.abandonChanges.title");
					
					title = SwingCreator.replacePlaceHolders(title, values);
					
					getDialog().setTitle(title);
				}
			});			
		}

		return fDlg;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>AbandonChangesAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(true);
		
		SwingUtils.showCentered(getFrame(), getDialog());

		if (ButtonConstants.OK == getDialog().getTerminatingButton()) {		
			System.out.println("abandon changes in model");
		
			setEnabled(false);
		}
	}
}