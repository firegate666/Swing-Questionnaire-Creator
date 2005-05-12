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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.OptionBinary;

public class OptionBinaryPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/** "SwitchLabel" situated BEFORE the Textfield */
	private LabelTextFieldPanel fUpperLabel;

	/** "SwitchLabel" situated AFTER the Textfield */
	private LabelTextFieldPanel fLowerLabel;

	/** CheckBox of the first choice */
	private JCheckBox fBox1;

	/** CheckBox of the Second choice */
	private JCheckBox fBox2;

	/** Panel who contains the first label/Checkbox pair */
	private JPanel fPanel1;

	/** Panel who contains the second label/Checkbox pair */
	private JPanel fPanel2;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////
	public OptionBinaryPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {
		super(type, AnswerType.OPTION_BINARY, config);
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
		setColor(Color.magenta);
		getContentPanel().add(getPnl1());
		getContentPanel().add(getPnl2());

	}

	/* ----------- Accessors --------------------- */
	/**
	 *  get the first Panel
	 *  
	 *  @return the Panel
	 */
	protected JPanel getPnl1() {
		if (null == fPanel1) {

			fUpperLabel = getLabel();
			fUpperLabel.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("value".equals(evt.getPropertyName())) {
						updateUpperLabelAction();
					}
				}
			});
			JPanel fPanel1 = new JPanel(new BorderLayout());

			fBox1 = new JCheckBox();
			fPanel1.add(fBox1, BorderLayout.WEST);
			fPanel1.add(fUpperLabel, BorderLayout.CENTER);
			return fPanel1;
		}
		fPanel1.setBackground(Color.cyan);
		return fPanel1;
	}

	/**
	 *  get the second Panel
	 *  
	 *  @return the Panel
	 */
	protected JPanel getPnl2() {
		if (null == fPanel2) {
			fLowerLabel = getLabel();
			fLowerLabel.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("value".equals(evt.getPropertyName())) {
						updateLowerLabelAction();
					}
				}
			});
			JPanel fPanel2 = new JPanel(new BorderLayout());

			fBox2 = new JCheckBox();
			fPanel2.add(fBox2, BorderLayout.WEST);
			fPanel2.add(fLowerLabel, BorderLayout.CENTER);
			return fPanel2;
		}

		return fPanel2;
	}

	/**
	 *	
	 * Get an appropriate LabelTextFieldPanel
	 *  
	 * @returns the Label
	 */
	protected LabelTextFieldPanel getLabel() {

		LabelTextFieldPanel result = new LabelTextFieldPanel();
		result.setValue(getResource("tflabel_default"));

		return result;

	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
	/** 
	 *	invoked if the text of the first <code>LabelTFPanel</code> changes
	 *  
	 */
	protected void updateUpperLabelAction() {
		((OptionBinary) getAnswerType()).setUpperLabel(fUpperLabel.getValue());
	}

	/** 
	 *	invoked if the text of the second <code>LabelTFPanel</code> changes
	 *  
	 */
	protected void updateLowerLabelAction() {
		((OptionBinary) getAnswerType()).setLowerLabel(fLowerLabel.getValue());
	}

}