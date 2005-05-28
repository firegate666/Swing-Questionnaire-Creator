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
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.sdavids.swing.controls.LabelTextFieldPanel;

public class MatchPanel extends JPanel {

	private int fSize;
	private JButton fAdd;
	private JPanel fAddPanel;

	//    public MatchPanel(IAnswerType answer, Questionaire app) {
	//        super(answer, app);
	//
	//        fSize = 1;
	//        fAdd = new JButton("add OptionPanel");
	//        fAddPanel = new JPanel();
	//
	//        
	//        fAdd.addActionListener(new ActionListener() {
	//            public void actionPerformed(ActionEvent evt) {
	//                addPanel();
	//            };
	//        });
	//
	//        fAddPanel.add(fAdd);
	//        fAddPanel.setPreferredSize(new Dimension(30, 30));
	//
	//        getContentPanel().add(fAddPanel, BorderLayout.NORTH);
	//    }

	protected void addPanel() {
		fSize++;

		JPanel pnl = new JPanel();

		pnl.setLayout(new GridLayout(fSize, fSize));
		pnl.add(new JLabel("  "));

		int i = 0;

		for (; i < fSize; i++) {
			LabelTextFieldPanel lbl = new LabelTextFieldPanel();

			lbl.setValue("???");

			pnl.add(lbl);
		}

		for (i = 0; i < (fSize - 1); i++) {
			LabelTextFieldPanel lbl = new LabelTextFieldPanel();

			lbl.setValue("???");

			pnl.add(lbl);

			for (int j = 0; j < fSize; j++) {
				pnl.add(new JRadioButton());
			}
		}

		add(pnl, BorderLayout.CENTER);
		revalidate();
	}

}