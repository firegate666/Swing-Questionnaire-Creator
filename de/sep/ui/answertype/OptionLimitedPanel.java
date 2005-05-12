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
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.awt.VerticalBagLayout;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.LabelTextFieldPanel;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.OptionLimited;

public class OptionLimitedPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////
	/** Panel containing the question's number. */
	private JPanel fNumberPnl;

	/** The Spinner containing the question's number in it's textfield. */
	private NumberSpinner fNumberTF;

	private JPanel fInfoPanel;

	/** NumberSpinner to change the number of columnss */
	private NumberSpinner fSelectableTF;

	/** contains the options */
	private JPanel fOptionPanel;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	public OptionLimitedPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.OPTION_LIMITED, config);

	}
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////   
	/** setup for this specific Answertype*/
	protected void initialize() {
		super.initialize();
		getContentPanel().setLayout(new BorderLayout());
		setColor(Color.orange);
		getContentPanel().add(getInfoPanel(), BorderLayout.NORTH);
		getContentPanel().add(getOptionPanel(), BorderLayout.WEST);
		addOption();
	}

	/**
	 * Set the corresponding AnswerType within the model
	 */
	protected void setAnswerType(IAnswerType answer) {
		super.setAnswerType(answer);

		(
			(OptionLimited) getAnswerType())
				.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String propName = evt.getPropertyName();

				if ("number".equals(propName)) {
					getNumberTF().setText(String.valueOf((Integer) evt.getNewValue()));

				}
				if ("count".equals(propName)) {
					getSelectableTF().setText(String.valueOf((Integer) evt.getNewValue()));

				}

			}
		});
	}

	/**
	 * add a new Option to the OptionPanel
	 * 
	 */
	protected void addOption() {
		JPanel temp = new JPanel(new BorderLayout());
		temp.add(new JCheckBox(), BorderLayout.WEST);
		temp.add(getNewLabelTextFieldPanel(), BorderLayout.CENTER);
		getOptionPanel().add(temp);
		getOptionPanel().revalidate();
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
	 * Answer the Panel containing Numberspinner  
	 * @return	the LabelTextFieldPanel.
	 */
	protected JPanel getInfoPanel() {
		if (null == fInfoPanel) {
			fInfoPanel = new JPanel();
			fInfoPanel.add(new JLabel(getResource("label_noption")));
			fInfoPanel.add(getNumberTF());
			fInfoPanel.add(new JLabel(getResource("label_selectable")));
			fInfoPanel.add(getSelectableTF());
		}
		return fInfoPanel;
	}

	/**
	 * Answer the Panel containig the options  
	 * @return	the Panel.
	 */
	protected JPanel getOptionPanel() {
		if (null == fOptionPanel) {
			fOptionPanel = new JPanel(new VerticalBagLayout());
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
	protected NumberSpinner getNumberTF() {
		if (null == fNumberTF) {
			fNumberTF =
				SwingCreator
					.newNumberSpinner(
						"optionlimitedpnl.optionNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						while (Integer.parseInt(fNumberTF.getText())
							> getOptionPanel().getComponentCount()) {
							addOption();
						}
						while (Integer.parseInt(fNumberTF.getText())
							< getOptionPanel().getComponentCount()) {
							removeOption();
						}

					}
				}
			});
		}

		return fNumberTF;
	}

	/**
	 * Answer the textfield containing the number of selectable options
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getSelectableTF() {
		if (null == fSelectableTF) {
			fSelectableTF =
				SwingCreator
					.newNumberSpinner(
						"optionlimitedpnl.selectableNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						if ((Integer.parseInt(getSelectableTF().getText())
							<= getOptionPanel().getComponentCount())) {
							updateSelectableAction();
						}
					}
				}
			});
		}

		return fSelectableTF;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////
	/**
	 *  invoked if the Numberspinner is changed
	 *
	 */
	protected void updateSelectableAction() {

		((OptionLimited) getAnswerType()).setSelectableCount(
			Integer.parseInt(getSelectableTF().getText()));

	}

	/**
	 *  invoked if the Options changed in any way
	 *
	 */
	protected void updateOptionsAction() {
		((OptionLimited) getAnswerType()).setOptions(getOptionPanel().getComponents());
		getOptionPanel().revalidate();
	}

}