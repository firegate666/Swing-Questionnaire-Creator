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

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import de.sep.ui.Questionaire;

/**
 * A <code>ScrollTopAction<code> is invoked if the user wants scroll the
 * questionaire to it's top.
 */
public class ScrollTopAction extends AbstractQuestionaireAction {

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Defines an <code>ScrollTopAction<code> object with the specified name and
	 * a reference to the application.
	 * 
	 * @param	<code>name<code> the name.
	 * @param	<code>app<code> a reference to the application.
	 */
	public ScrollTopAction(String name, Questionaire app) {
		super(name, app);
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT ACTIONLISTENER
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when an <code>ScrollTopAction<code> occurs.
	 * 
	 * @param	<code>evt<code> the <code>ActionEvent</code>.
	 */
	public void actionPerformed(ActionEvent evt) {
		JPanel pnl = ((Questionaire) getApp()).getQuestionairePanel();
							
		pnl.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
		pnl.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
	}
}