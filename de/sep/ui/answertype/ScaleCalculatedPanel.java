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

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.ScaleCalculated;

public class ScaleCalculatedPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** NumberSpinner to change the number of rows */
	private NumberSpinner fStartTF;

	/** NumberSpinner to change the number of columnss */
	private NumberSpinner fEndTF;

	/** Text of the left pol */
	private LabelTextFieldPanel fLeftPol;

	/** text of the right pol */
	private LabelTextFieldPanel fRightPol;

	/** Panel that contains the Texts  */
	private JPanel fIntervalPanel;

	/** Panel that contains the numberspinner */
	private JPanel fGridPanel;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////	

	public ScaleCalculatedPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.SCALE_CALCULATED, config);

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

		setColor(Color.darkGray);

		getContentPanel().add(getGridPanel(), BorderLayout.SOUTH);
		getContentPanel().add(getIntervalPanel(), BorderLayout.NORTH);
		updateLeftTextAction();
		updateRightTextAction();
		updateLeftPolAction();
		updateRightPolAction();

	}

	/**
	 * Set the corresponding AnswerType within the model
	 */

	protected void setAnswerType(IAnswerType answer) {
		super.setAnswerType(answer);

		(
			(ScaleCalculated) getAnswerType())
				.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String propName = evt.getPropertyName();

				if ("lefttxt".equals(propName)) {
					getLeftPolTF().setValue(((String) evt.getNewValue()));
				}
				if ("righttxt".equals(propName)) {
					getRightPolTF().setValue(((String) evt.getNewValue()));
				}
				if ("leftnumber".equals(propName)) {
					getStartTF().setText(String.valueOf((Integer) evt.getNewValue()));
				}
				if ("avgnumber".equals(propName)) {
					//we will ignore that for the moment
				}
				if ("rightnumber".equals(propName)) {
					getEndTF().setText(String.valueOf((Integer) evt.getNewValue()));
				}
			}
		});
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer the the Panel that contains the Texts for the pols
	 * 
	 * @return JPanel
	 */
	protected JPanel getIntervalPanel() {

		if (null == fIntervalPanel) {
			fIntervalPanel = new JPanel();
			fIntervalPanel.add(new JLabel(getResource("label_lefttxt")));
			fIntervalPanel.add(getLeftPolTF());
			fIntervalPanel.add(new JLabel(getResource("label_righttxt")));
			fIntervalPanel.add(getRightPolTF());
		}

		return fIntervalPanel;
	}

	/**
	 * Answer the the Panel that contains the Numberspinner for the pols
	 * 
	 * @return JPanel
	 */
	protected JPanel getGridPanel() {
		if (null == fGridPanel) {
			fGridPanel = new JPanel();
			fGridPanel.add(new JLabel(getResource("label_leftnumber")));
			fGridPanel.add(getStartTF());
			fGridPanel.add(new JLabel(getResource("label_rightnumber")));
			fGridPanel.add(getEndTF());
		}
		return fGridPanel;
	}

	/**
	 * Answer the textfield containing the number for the left pol
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getStartTF() {
		if (null == fStartTF) {
			fStartTF =
				SwingCreator
					.newNumberSpinner(
						"scalecalculatedpnl.startNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateLeftPolAction();
					}
				}
			});
		}

		return fStartTF;
	}

	/**
	 * Answer the textfield containing the number for the right pol
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getEndTF() {
		if (null == fEndTF) {
			fEndTF =
				SwingCreator
					.newNumberSpinner(
						"scalecalculatedpnl.endNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateRightPolAction();
					}
				}
			});
		}

		return fEndTF;
	}

	/**
	 * Answer the LabelTextFieldPanel for the left pol
	 * 
	 * @return		LabelTextFieldPanel for the left pol
	 * @see		de.sdavids.swing.controls.LabelTextFieldPanel.
	 */
	protected LabelTextFieldPanel getLeftPolTF() {
		if (null == fLeftPol) {
			fLeftPol =
				new LabelTextFieldPanel(getResource("tflabel_default"), "     ", true);
			fLeftPol.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("value".equals(evt.getPropertyName())) {
						updateLeftTextAction();
					}
				}
			});
		}
		return fLeftPol;
	}

	/**
	 * Answer the LabelTextFieldPanel for the right pol
	 * 
	 * @return	LabelTextFieldPanel for the right pol
	 * @see		de.sdavids.swing.controls.LabelTextFieldPanel.
	 */
	protected LabelTextFieldPanel getRightPolTF() {
		if (null == fRightPol) {
			fRightPol =
				new LabelTextFieldPanel(getResource("tflabel_default"), "     ", true);
			fRightPol.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("value".equals(evt.getPropertyName())) {
						updateRightTextAction();
					}
				}
			});
		}
		return fRightPol;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/** 
	 *	invoked if the text of <code>LabelTFPanel</code> for the left pol changes
	 *  
	 */
	protected void updateLeftTextAction() {
		((ScaleCalculated) getAnswerType()).setLeftText(getLeftPolTF().getValue());
	}

	/** 
	 *	invoked if the text of <code>LabelTFPanel</code> for the right pol changes
	 *  
	 */
	protected void updateRightTextAction() {
		((ScaleCalculated) getAnswerType()).setRightText(getRightPolTF().getValue());
	}

	/** 
	 *	invoked if the  <code>NumberSpinner</code> for the left Pol changes
	 *  
	 */
	protected void updateLeftPolAction() {
		((ScaleCalculated) getAnswerType()).setLeft(
			Integer.parseInt(getStartTF().getText()));
	}

	/** 
	 *	invoked if the  <code>NumberSpinner</code> for the right Pol changes
	 *  
	 */
	protected void updateRightPolAction() {
		((ScaleCalculated) getAnswerType()).setRight(
			Integer.parseInt(getEndTF().getText()));
	}

}