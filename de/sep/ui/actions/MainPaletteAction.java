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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import de.sep.ui.MainPalette;
import de.sep.ui.Questionaire;

/**
 * A <code>MainPaletteAction<code> is invoked if the user wants hide or unhide
 * the main palette.
 */
public class MainPaletteAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Palette contains the different answer types. */
	private MainPalette fPalette;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>MainPaletteAction<code> object with the specified name and
	 * a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application. 
	 */
	public MainPaletteAction(String name, Questionaire app) {
		super(name, app);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors --------------------- */

	/**
	 * Answer the palette containing the answer types.
	 * 
	 * @return	the palette.
	 */
	public MainPalette getPalette() {
		if (null == fPalette) {
			fPalette = new MainPalette((Questionaire) getApp());

			((Questionaire) getApp()).getRootPanel().add(fPalette, BorderLayout.NORTH);
		}

		return fPalette;
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when a <code>MainPaletteAction<code> occurs.
	 * 
	 * Hides or unhides the main palette.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();

		if (getName().equals(cmd)) { //non-I18N-NLS
			getPalette().setVisible(!getPalette().isVisible());
		} else if ("show".equals(cmd)) { //non-I18N-NLS
			getPalette().setVisible(true);
		} else if ("hide".equals(cmd)) { //non-I18N-NLS
			getPalette().setVisible(false);
		} else if ("showAnswerTypes".equals(cmd)) { //non-I18N-NLS
			getPalette().toggleAnswerTypeJDragLabels(true);
		} else if ("hideAnswerTypes".equals(cmd)) { //non-I18N-NLS
			getPalette().toggleAnswerTypeJDragLabels(false);
		}
	}
}