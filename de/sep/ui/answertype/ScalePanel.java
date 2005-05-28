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

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.sdavids.swing.controls.LabelTextFieldPanel;

public class ScalePanel extends JPanel {

	JPanel fButtons;
	JPanel fLabels;

	int fCounter = 0;

//	public ScalePanel(IAnswerType answer, Questionaire app) {
//		super(answer, app);
//
//		fButtons = new JPanel();
//		fLabels = new JPanel();
//
//		JButton add = new JButton("+");
//
//		add.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				addOption();
//			};
//		});
//
//		JPanel addPanel = new JPanel();
//
//		addPanel.add(add);
//		addPanel.setPreferredSize(new Dimension(30, 30));
//
//		JPanel medium = new JPanel();
//
//		medium.setLayout(new VerticalBagLayout(5));
//
//		medium.add(fLabels);
//		medium.add(fButtons);
//
//		JPanel grand = new JPanel();
//
//		grand.add(new LabelTextFieldPanel("???"));
//		grand.add(medium);
//		grand.add(new LabelTextFieldPanel("???"));
//
//		add(addPanel);
//		add(grand);
//	}

	protected void addOption() {
		fCounter++;

		fLabels.add(new LabelTextFieldPanel("???"));
		fButtons.add(new JRadioButton());

		revalidate();
	}

	

}