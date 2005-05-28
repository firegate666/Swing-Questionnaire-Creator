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
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.OpenQuantified;

public class OpenQuantifiedPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* "SwitchLabel" situated BEFORE the Textfield */
	private LabelTextFieldPanel fLeftLabel;

	/* "SwitchLabel" situated AFTER the Textfield */
	private LabelTextFieldPanel fRightLabel;

	/* Textfield for the quantified answer */
	private JTextField fText;

	/* NumberSpinner to change the number of columns */
	private NumberSpinner fColTF;

	/* NumberSpinner to change the min of fText*/
	private NumberSpinner fMinTF;

	/* NumberSpinner to change the max of fText */
	private NumberSpinner fMaxTF;

	/* contains the two LTFs and the TextField*/
	private JPanel fCentralPanel;

	/* contains the LTF before the TextField */
	private JPanel fTxtBeforePanel;

	/* contains the LTF after the TextField */
	private JPanel fTxtAfterPanel;

	/* contains then min and max spinner */
	private JPanel fGridPanel;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	//////////////////////////////////////////////////////////////////////////////

	public OpenQuantifiedPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.OPEN_QUANTIFIED, config);
	}

	//////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 *  Setup for this AnswerTypePanel
	 *
	 */
	protected void initialize() {

		super.initialize();
		getContentPanel().setLayout(new BorderLayout());

		setColor(Color.lightGray);
		getContentPanel().add(getGridPanel(), BorderLayout.WEST);
		getContentPanel().add(getCentralPanel(), BorderLayout.CENTER);

		updateMaxAction();
		updateMinAction();
	}

	/* ----------- Accessors --------------------- */

	/**
	 *
	 * Get an appropriate LabelTFPanel
	 *
	 * @returns the Label
	 */
	protected LabelTextFieldPanel getLabel() {
		LabelTextFieldPanel result = new LabelTextFieldPanel();
		result.setValue(getResource("tflabel_default"));
		result.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName())) {
					updateLeftTextAction();
					updateRightTextAction();
				}
			}
		});
		return result;
	}

	/**
	 *
	 * Answer the JPanel that contains the min and max spinner
	 * 
	 * @returns the panel
	 */
	protected JPanel getGridPanel() {
		if (null == fGridPanel) {
			fGridPanel = new JPanel(new GridLayout(2, 2));
			fGridPanel.add(new JLabel(getResource("label_min")));
			fGridPanel.add(getMinTF());
			fGridPanel.add(new JLabel(getResource("label_max")));
			fGridPanel.add(getMaxTF());
			return fGridPanel;
		}
		return fGridPanel;
	}

	/**
	 *
	 * Answer the JPanel that contains two LTFs and the textfield
	 *
	 * @returns the panel
	 */
	protected JPanel getCentralPanel() {
		if (null == fCentralPanel) {
			fCentralPanel = new JPanel(new GridLayout(2, 2));
			fCentralPanel.add(getTxtBeforePanel());
			fCentralPanel.add(getTxtAfterPanel());
			return fCentralPanel;
		}
		return fCentralPanel;
	}
	/**
	 *
	 * Answer the JPanel that contains the LTF before the textfield
	 *
	 * @returns the panel
	 */
	protected JPanel getTxtBeforePanel() {
		if (null == fTxtBeforePanel) {
			fTxtBeforePanel = new JPanel(new BorderLayout());
			fTxtBeforePanel.add(new JLabel(getResource("label_before")), BorderLayout.WEST);
			fLeftLabel = getLabel();
			fTxtBeforePanel.add(fLeftLabel, BorderLayout.CENTER);
			return fTxtBeforePanel;
		}
		return fTxtBeforePanel;
	}
	/**
	 *
	 * Answer the JPanel that contains the LTF after the textfield
	 *
	 * @returns the panel
	 */
	protected JPanel getTxtAfterPanel() {
		if (null == fTxtAfterPanel) {
			fTxtAfterPanel = new JPanel(new BorderLayout());
			fTxtAfterPanel.add(new JLabel(getResource("label_after")), BorderLayout.WEST);
			fRightLabel = getLabel();
			fTxtAfterPanel.add(fRightLabel, BorderLayout.CENTER);
			return fTxtAfterPanel;
		}
		return fTxtAfterPanel;
	}

	/**
	 * Answer the textfield containing minimum of quantum to be entered.
	 *
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 *
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getMinTF() {
		if (null == fMinTF) {
			fMinTF =
				SwingCreator
					.newNumberSpinner(
						"openQuantifiedPnl.minNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateMinAction();
					}
				}
			});
		}

		return fMinTF;
	}

	/**
	 * Answer the textfield containing maximum of quantum to be entered.
	 *
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 *
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getMaxTF() {
		if (null == fMaxTF) {
			fMaxTF =
				SwingCreator
					.newNumberSpinner(
						"openQuantifiedPnl.maxNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateMaxAction();
					}
				}
			});
		}

		return fMaxTF;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
	/**
	 *  invoked if <code>fLeftLabel</code> is changed
	 *
	 */
	protected void updateLeftTextAction() {
		((OpenQuantified) getAnswerType()).setLeftText(fLeftLabel.getValue());
	}

	/**
	 *  invoked if <code>fRightLabel</code> is changed
	 *
	 */
	protected void updateRightTextAction() {
		((OpenQuantified) getAnswerType()).setRightText(fRightLabel.getValue());
	}

	/**
	 *  invoked if <code>fMinTF</code> is changed
	 *
	 */
	protected void updateMinAction() {
		try {
			((OpenQuantified) getAnswerType()).setMinValue(
				Integer.parseInt(getMinTF().getText()));
		} catch (NumberFormatException e) {
			//ignore; no change in model
		}
	}

	/**
	 *  invoked if <code>fMaxTF</code> is changed
	 *
	 */
	protected void updateMaxAction() {
		try {
			((OpenQuantified) getAnswerType()).setMaxValue(
				Integer.parseInt(getMaxTF().getText()));
			((OpenQuantified) getAnswerType()).setColumns((getMaxTF().getText().length()));
		} catch (NumberFormatException e) {
			//ignore; no change in model
		}

	}

}