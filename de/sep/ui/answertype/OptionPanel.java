package de.sep.ui.answertype;

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

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;

public class OptionPanel extends JPanel{
	//implements ActionListener{

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** NumberSpinner to change the number of rows */
	private NumberSpinner fOptionNS;
		
	private JPanel fAddPanel;
	
	private JPanel fOptions;

//////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
//////////////////////////////////////////////////////////////////////////////

	public OptionPanel() {
		
		//fAddPanel.add(fAdd);
		
	}

	private void addOption() {
		JPanel fOptions = new JPanel(new BorderLayout());
		fOptions.add(new JCheckBox(),BorderLayout.WEST);
		LabelTextFieldPanel fTF = new LabelTextFieldPanel();
		fTF.setValue("???");
		fOptions.add(fTF,BorderLayout.CENTER);
		//getContentPanel().add(fOptions);
		this.revalidate();
	}


}