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
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ContainerEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import sun.awt.VerticalBagLayout;

import de.sdavids.dnd.DropTargetAdapter;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.ui.Questionaire;

abstract class AbstractAnswerTypePanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/** A reference to the corresponding AnswerType. */
	private IAnswerType fAnswer;

	/** A reference to the application. */
	private Questionaire fApp;
	
	/** the Border around AnswerTypePanels */
	private LineBorder fBorder;
		
	/** JPanel that indicates the AnswerType by color */
	private JPanel fColorPnl;
	
	/** JPanel that will hold the content */
	private JPanel fContentPnl;
	
	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	  *  constructor which should be only invoked by the subclasses
	  */
	protected AbstractAnswerTypePanel(
		IAnswerType answer,
		AnswerType type,
		ResourceBundle config)
		throws IllegalAnswerTypeException {

		if (!type.equals(answer.getType()))
			throw new IllegalAnswerTypeException(String.valueOf(answer.getType()));

		setAnswerType(answer);
		setResources(config);
		
		initialize();
	}


	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the dialog's configuration.
	 *
	 * @param	<code>config</code> the bundle.
	 */
	protected void setResources(ResourceBundle config) {
		fConfig = config;
	}

	protected ResourceBundle getResources() {
		return fConfig;
	}

	/**
	* Set the reference to the application.
	*
	* @param	the reference.
	*/
	protected void initialize() {
		setLayout(new BorderLayout());
		
		add(getColorPanel(), BorderLayout.WEST);
		add(getContentPanel(), BorderLayout.CENTER);
	}
	
	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setColor(Color color) {
	fColorPnl.setBackground(color);
	}

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setApp(Questionaire app) {

		fApp = app;
	}

	protected void setAnswerType(IAnswerType answer) {
		fAnswer = answer;
	}

	/**
	 * Answer the string resource with the given key.
	 *
	 * @return    the resource.
	 */
	protected String getResource(String key) {
		return getResources().getString(key);
	}

	protected JPanel getColorPanel(){
		if(null == fColorPnl){
	 		fColorPnl = new JPanel();
	 		fColorPnl.setBorder(BorderFactory.createLineBorder(Color.darkGray));
	 	}
	 	return fColorPnl;
	}
	
	
	protected JPanel getContentPanel(){
		if(null == fContentPnl){
			fContentPnl = new JPanel(new VerticalBagLayout());
		
		}
		return fContentPnl;
	}
	
	/**
	 * Answer the reference to the application.
	 *
	 * @return	the reference.
	 */
	protected Questionaire getApp() {
		return fApp;
	}

	/**
	 * Answer the AnswerType
	 * 
	 * @return	the answerType
	 */
	protected IAnswerType getAnswerType() {
		return fAnswer;
	}
	//


	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	//////////////////////////////////////////////////////////////////////////////

	/**
	* Used for the Container Listener, if a component is added
	* @param e ContainerEvent that has been triggered
	*/
	public void componentAdded(ContainerEvent e) {
		revalidate();
	}

	/**
	* Used for the Container Listener, if a component is removed
	* @param e ContainerEvent that has been triggered
	*/
	public void componentRemoved(ContainerEvent e) {
		revalidate();
	}

}