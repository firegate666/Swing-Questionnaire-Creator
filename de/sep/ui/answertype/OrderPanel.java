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
import sun.awt.VerticalBagLayout;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.Order;

public class OrderPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** JPanel that contains the Numberspinner <code>fOptionTF</code> */
	private JPanel fSpinnerPanel;

	/** JPanel that contains the Options */
	private JPanel fOptionPanel;

	/** NumberSpinner to change the number of Options */
	private NumberSpinner fOptionTF;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	public OrderPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.ORDER, config);

	}
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Does the setup for this AnswerType
	 * 
	 */
	protected void initialize() {
		super.initialize();

		setColor(Color.red);

		getContentPanel().setLayout(new BorderLayout());

		getContentPanel().add(getSpinnerPanel(), BorderLayout.NORTH);
		getContentPanel().add(getOptionPanel(), BorderLayout.WEST);
		addOption();
	}

	/**
	 * add a new Option to the OptionPanel
	 * 
	 */
	protected void addOption() {
		fOptionPanel.add(getNewLabelTextFieldPanel());
		getContentPanel().revalidate();
		updateOptionsAction();
	}

	/**
	 * remove the Option from the OptionPanel that was the last one added 
	 * 
	 */
	protected void removeOption() {
		getOptionPanel().remove(getOptionPanel().getComponentCount() - 1);
		getContentPanel().revalidate();
		updateOptionsAction();
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer the textfield containing the number of options.
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getOptionTF() {
		if (null == fOptionTF) {
			fOptionTF =
				SwingCreator
					.newNumberSpinner(
						"orderPnl.optionNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						while (Integer.parseInt(fOptionTF.getText())
							> getOptionPanel().getComponentCount()) {
							addOption();
						}
						while (Integer.parseInt(fOptionTF.getText())
							< getOptionPanel().getComponentCount()) {
							removeOption();
						}

					}
				}
			});
		}

		return fOptionTF;
	}

	/**
	 * Answer a new LabelTextFieldPanel  
	 * @return	the LabelTextFieldPanel.
	 */
	protected LabelTextFieldPanel getNewLabelTextFieldPanel() {
		LabelTextFieldPanel result =
			new LabelTextFieldPanel(getResource("tflabel_default"), "     ", true);
		result.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName())) {
					updateOptionsAction();
				}
			}
		});
		return result;
	}

	/**
	 * Answer the OptionPanel that contains the Options 
	 * @return	the Panel.
	 */
	protected JPanel getOptionPanel() {
		if (null == fOptionPanel) {
			fOptionPanel = new JPanel(new VerticalBagLayout());
		}
		return fOptionPanel;
	}

	/**
	 * Answer the SpinnerPanel, that contains the Numberspinner  
	 * @return	the Panel.
	 */
	protected JPanel getSpinnerPanel() {
		if (null == fSpinnerPanel) {
			fSpinnerPanel = new JPanel();
			fSpinnerPanel.add(new JLabel(getResource("label_option")));
			fSpinnerPanel.add(getOptionTF());
		}
		return fSpinnerPanel;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
	/**
	 *  invoked if the Options changed in any way
	 *
	 */
	protected void updateOptionsAction() {
		((Order) getAnswerType()).setOptions(getOptionPanel().getComponents());
		getOptionPanel().revalidate();
	}
}