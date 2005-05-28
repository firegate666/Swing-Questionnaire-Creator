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

import javax.swing.JPanel;
import javax.swing.JTextField;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.CompletionOpen;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;

public class CompletionOpenPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/** Textfield for the quantified answer */
	private JTextField fText;

	/** NumberSpinner to change the number orderable options */
	private NumberSpinner fOptionNS;

	/** JPanel that contains the to LTFs and the TextField*/
	private JPanel fSpinnerPanel;

	/** JPanel that contains the to LTFs and the TextField*/
	private JPanel fOptionPanel;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	public CompletionOpenPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.COMPLETION_OPEN, config);
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

		setColor(Color.cyan);

		getContentPanel().add(getOptionPanel(), BorderLayout.SOUTH);
		getContentPanel().add(getSpinnerPanel(), BorderLayout.NORTH);
		addOption();

	}
	
	/**
	 * add a new Option to the OptionPanel
	 * 
	 */
	protected void addOption() {
		getOptionPanel().add(getNewLabelTextFieldPanel());
		getContentPanel().revalidate();
		updateOptionAction();
	}
	
	/**
	 * remove the Option from the OptionPanel that was the last one added 
	 * 
	 */
	protected void removeOption() {
		getOptionPanel().remove(getOptionPanel().getComponentCount() - 1);
		getContentPanel().revalidate();
		updateOptionAction();
	}
	
	/* ----------- Accessors --------------------- */
	/**
	 * Answer the SpinnerPanel that contains the spinner for the options 
	 * @return	the Panel.
	 */
	protected JPanel getSpinnerPanel() {
		if (null == fSpinnerPanel) {
			fSpinnerPanel = new JPanel();
			fSpinnerPanel.add(getOptionSpinner());
		}
		return fSpinnerPanel;
	}

	/**
	 * Answer the OptionPanel that contains the Options 
	 * @return	the Panel.
	 */
	protected JPanel getOptionPanel() {
		if (null == fOptionPanel) {
			fOptionPanel = new JPanel();
		}
		return fOptionPanel;
	}
	
	/**
	 * Answer the textfield containing the number of options.
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getOptionSpinner() {
		if (null == fOptionNS) {
			fOptionNS =
				SwingCreator
					.newNumberSpinner(
						"completionopenpnl.optionNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {

						while (Integer.parseInt(fOptionNS.getText())
							> getOptionPanel().getComponentCount()) {
							addOption();
						}
						while (Integer.parseInt(fOptionNS.getText())
							< getOptionPanel().getComponentCount()) {
							removeOption();
						}

					}
				}
			});
		}

		return fOptionNS;
	}
	
	/**
	 * Answer a new LabelTextFieldPanel  
	 * @return	the LabelTextFieldPanel.
	 */
	protected LabelTextFieldPanel getNewLabelTextFieldPanel() {
		LabelTextFieldPanel result = new LabelTextFieldPanel(getResource("tflabel_default"), "     ", true);
		result.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName())) {
					updateOptionAction();
				}
			}
		});
		return result;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
	/**
	 *  invoked if the Orderabels changed in any way
	 *
	 */
	protected void updateOptionAction() {
		((CompletionOpen) getAnswerType()).setGivenOptions(
			getOptionPanel().getComponents());
		getOptionPanel().revalidate();

	}
}