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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.interfaces.IAuthored;
import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.SwingUtils;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;
import de.sep.ui.dialog.ChangeAuthorDialog;

/**
 * A <code>ChangeAuthorAction<code> is invoked if the user wants to change the
 * author of an existing questionaire.
 */
public class ChangeAuthorAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Non-modal dialog for entering the authors first and last name. */
	private ChangeAuthorDialog fDlg;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>ChangeAuthorAction<code> object with the specified name
	 * and a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public ChangeAuthorAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Updates the model's author.
	 */
	protected void execute() {
		IQuestionaire doc = ((Questionaire) getApp()).getCurrentDoc();
		IPerson docAuthor = doc.getAuthor();
		IPerson author = getDialog().getAuthor();

		setEnabled(true);

		if (docAuthor.equals(author))
			return;

		((Questionaire) getApp()).getCurrentDoc().setAuthor(author);
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer a dialog through which the user can change the author.
	 * 
	 * @return	the dialog.
	 */
	protected ChangeAuthorDialog getDialog() {
		if (null == fDlg) {
			fDlg =
				new ChangeAuthorDialog(
					getFrame(),
					getConfig(),
					((IAuthored) ((Questionaire) getApp()).getCurrentDoc()).getAuthor());

			fDlg.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					execute();
				}
			});
			
			String[] values = { ((Questionaire) getApp()).getCurrentDoc().getTitle() };
			
			String title = fDlg.getTitle();
						
			title = SwingCreator.replacePlaceHolders(title, values);
						
			fDlg.setTitle(title);
						
			((Questionaire) getApp()).getCurrentDoc().addPropertyChangeListener("title", new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					String[] values = { (String) evt.getNewValue() };
					String title = getConfig("dlg.changeAuthor.title");
					
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
	 * Invoked when an <code>ChangeAuthorAction<code> occurs.
	 * 
	 * Displays a non-modal dialog.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		setEnabled(false);

		IPerson author = ((Questionaire) getApp()).getCurrentDoc().getAuthor();

		if (!author.equals(getDialog().getAuthor()))
			getDialog().setAuthor(author);

		SwingUtils.showCentered(getFrame(), getDialog());
	}
}