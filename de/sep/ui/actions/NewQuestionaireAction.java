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
import de.sep.ui.dialog.NewQuestionaireDialog;

/**
 * A <code>NewQuestionaireAction<code> is invoked if the user wants to create a new
 * questionaire.
 */
public class NewQuestionaireAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Modal dialog for entering the questionaires name. */
	private NewQuestionaireDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>NewQuestionaireAction<code> object with the specified name,
	 * icon, and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public NewQuestionaireAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Creates a new questionaire.
	 */
	protected void execute() {
		String title = getDialog().getQuestionaireTitle();

		setEnabled(true);

		IQuestionaire doc = ((Questionaire) getApp()).getCurrentDoc();
		
		if (("".equals(title)) || (doc.getTitle().equals(title)))
			return;

		if (!doc.isNull()) {
			//Abfrage ob schon ein Document unter dem Namen besteht
			performAction(ACTION_SAVE, doc, doc.hashCode(), "save");
			//non-I18N-NLS
		}

		((Questionaire) getApp()).setCurrentDoc(((Questionaire) getApp()).getFactory().createQuestionaire(title));
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog for creating a new questionaire.
	 * 
	 * @return	the dialog.
	 */
	public NewQuestionaireDialog getDialog() {
		if (null == fDlg) {
			fDlg = new NewQuestionaireDialog(((Questionaire) getApp()));

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
	 * Invoked when a <code>NewQuestionaireAction<code> occurs.
	 * 
	 * Displays a modal dialog.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(false);

		SwingUtils.showCentered(getFrame(), getDialog());
	}
}