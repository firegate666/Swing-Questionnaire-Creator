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
import javax.swing.JTextArea;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.NumberSpinner;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.model.answertype.OpenQualified;

public class OpenQualifiedPanel extends AbstractAnswerTypePanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** NumberSpinner to change the number of rows */
	private NumberSpinner fRowTF;

	/** NumberSpinner to change the number of columnss */
	private NumberSpinner fColTF;

	/** JPanel that contains the Textarea*/
	private JPanel fTextPnl;

	/** Textarea for the qualified answer */
	private JTextArea fAnswerTextArea;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	public OpenQualifiedPanel(IAnswerType type, ResourceBundle config)
		throws IllegalAnswerTypeException {

		super(type, AnswerType.OPEN_QUALIFIED, config);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 *  Setup for this AnswerTypePanel
	 *  
	 */
	protected void initialize() {
		super.initialize(); //SD-MK-solange D&D in Oberklasse raus
		getContentPanel().setLayout(new BorderLayout());
		getContentPanel().add(getRowTF(), BorderLayout.WEST);
		getContentPanel().add(getColTF(), BorderLayout.NORTH);
		getContentPanel().add(getTextPanel(), BorderLayout.CENTER);
		setColor(Color.green);
		updateColAction();
		updateRowAction();
	}

	/**
	 * Set the corresponding AnswerType within the model
	 */

	protected void setAnswerType(IAnswerType answer) {
		super.setAnswerType(answer);

		(
			(OpenQualified) getAnswerType())
				.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String propName = evt.getPropertyName();

				if ("rows".equals(propName)) {
					getRowTF().setText(String.valueOf((Integer) evt.getNewValue()));
					getAnswerTextArea().setRows(((Integer) evt.getNewValue()).intValue());
				}
				if ("columns".equals(propName)) {
					getColTF().setText(String.valueOf((Integer) evt.getNewValue()));
					getAnswerTextArea().setColumns(((Integer) evt.getNewValue()).intValue());
				}

			}
		});
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer the textfield containing the number of rows.
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getRowTF() {
		if (null == fRowTF) {
			fRowTF =
				SwingCreator
					.newNumberSpinner(
						"openQualifiedPnl.rowNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateRowAction();
					}
				}
			});
		}

		return fRowTF;
	}

	/**
	 * Answer the textfield containing the number of columns.
	 * 
	 * The textfield is wrapped inside a <code>NumberSpinner</code>.
	 * 
	 * @return	the textfield (spinner).
	 * @see		de.sdavids.swing.controls.NumberSpinner.
	 */
	protected NumberSpinner getColTF() {
		if (null == fColTF) {
			fColTF =
				SwingCreator
					.newNumberSpinner(
						"openQualifiedPnl.colNS",
						getResources(),
						new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("text".equals(evt.getPropertyName())) {
						updateColAction();
					}
				}
			});
		}

		return fColTF;
	}

	/**
	 * Answer the AnswerTextArea
	 * 
	 * @return	the JTextArea
	 */
	protected JTextArea getAnswerTextArea() {
		if (null == fAnswerTextArea) {
			fAnswerTextArea =
				new JTextArea(
					Integer.parseInt(getColTF().getText()),
					Integer.parseInt(getRowTF().getText()));
			fAnswerTextArea.setEditable(false);
		}
		return fAnswerTextArea;
	}

	/**
	 * Answer the Panel which holds the text
	 * 
	 * @return	the JPanel
	 */
	protected JPanel getTextPanel() {
		if (null == fTextPnl) {
			fTextPnl = new JPanel();
			fTextPnl.add(getAnswerTextArea(), BorderLayout.CENTER);
		}
		return fTextPnl;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the number of rows is changed.
	 */
	protected void updateRowAction() {
		try {
			((OpenQualified) getAnswerType()).setRows(
				Integer.parseInt(getRowTF().getText()));
		} catch (NumberFormatException e) {
			//ignore; no change in model    
		}
	}

	/**
	 * Invoked when the number of columns is changed.
	 */
	protected void updateColAction() {
		try {
			((OpenQualified) getAnswerType()).setColumns(
				Integer.parseInt(getColTF().getText()));
		} catch (NumberFormatException e) {
			//ignore; no change in model    
		}
	}

}