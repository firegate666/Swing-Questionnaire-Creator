package de.sep.ui;

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
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.actions.ActionImpl;;

/**
 * The status bar at the bottom of the application.
 */
public class QuestionaireStatusBar extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Indicator for the HTML-Mode: either HTML-preview or normal editing mode. */
	private JToggleButton fHTMLIndicator;

	/** The configuration. */	
	private ActionImpl fHTMLIndicatorAction;

	/** The label displaying the status message. */	
	private JLabel fStatusLbl;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create a status bar.
	 *
	 * @param	<code>bundle</code> the configuration.
	 */
	public QuestionaireStatusBar(ActionImpl htmlIndicatorAction) {
		super();

		setHTMLIndicatorAction(htmlIndicatorAction);
		
		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// CONTROLS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the HTML indicator button.
	 *
	 * @return	the button.
	 */
	protected JToggleButton getHTMLIndicator() {
		if (null == fHTMLIndicator) {
			fHTMLIndicator = SwingCreator.newJToggleButton(getHTMLIndicatorAction()); //non-I18N-NLS
			
			Font f = fHTMLIndicator.getFont();
			
			fHTMLIndicator.setFont(f.deriveFont((f.getSize2D() * getFontScaleFactor())));

			fHTMLIndicator.setMargin(new Insets(2, 2, 2, 2));
		}

		return fHTMLIndicator;
	}

	/**
	 * Answer status panel containing the status message.
	 *
	 * @return	the panel.
	 */
	protected JPanel getStatusPnl() {
		JPanel result = new JPanel(new BorderLayout());

		result
			.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createLoweredBevelBorder(),
					BorderFactory.createEmptyBorder(0, 3, 0, 0)));

		result.add(getStatusLbl(), BorderLayout.WEST);

		return result;
		
	}

	/**
	 * Answer label displaying the status message.
	 *
	 * @return	the label.
	 */
	protected JLabel getStatusLbl() {
		if (null == fStatusLbl) {
			fStatusLbl = new JLabel();
			
		}

		return fStatusLbl;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */
	
	/**
	 * Show the HTML indicator button.
	 */
	public void showHTMLIndicator() {
		getHTMLIndicator().setVisible(true);
	}

	/**
	 * Hide the HTML indicator button.
	 */	
	public void hideHTMLIndicator() {
		getHTMLIndicator().setVisible(false);
	}
	
	/**
	 * Update the status message.
	 * 
	 * @param <code>status</code> the message.
	 */	
	public void setStatus(String status) {
		if (status.equals(getStatusLbl().getText()))
			return;
			
		getStatusLbl().setText(status);
		getStatusLbl().revalidate();
	}
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the panel.
	 *
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		setLayout(new BorderLayout(3, 0));

		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		add(getHTMLIndicator(), BorderLayout.EAST);
		add(getStatusPnl(), BorderLayout.CENTER);
	}

	/**
	 * Set the <code>ActionImpl</code> corresponding to the HTML indicator button.
	 *
	 * L@param	<code>htmlIndicatorAction</code> the action.
	 */		
	protected void setHTMLIndicatorAction(ActionImpl htmlIndicatorAction) {
		fHTMLIndicatorAction = htmlIndicatorAction;
	}	

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the <code>ActionImpl</code> corresponding to the HTML indicator button.
	 *
	 * @return	the action.
	 */		
	protected ActionImpl getHTMLIndicatorAction() {
		return fHTMLIndicatorAction;
	}	

	/**
	 * Answer the scale factor for the font size of the status bar buttons.
	 *
	 * @return	the scale factor.
	 */		
	protected float getFontScaleFactor() {
		return 0.75f;
	}	
}
