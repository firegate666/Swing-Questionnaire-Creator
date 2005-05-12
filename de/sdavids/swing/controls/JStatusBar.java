/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
 
package de.sdavids.swing.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The status bar at the bottom of the application.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.00	02-01-13
 */
public class JStatusBar extends JPanel {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The label displaying the status message. */
	private JLabel fStatusLbl;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Create a status bar.
	 */
	public JStatusBar() {
		super();

		initialize();
	}

	//////////////////////////////////////////////////////////////////////////	
	// WIDGETS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Answer status panel containing the status message.
	 *
	 * @return	the panel.
	 */
	protected JPanel getStatusPnl() {
		JPanel result = new JPanel(new BorderLayout());

		result.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(0, 3, 0, 0)));

		result.add(getStatusLbl(), BorderLayout.WEST);

		return result;
	}

	/**
	 * Answer the label displaying the status message.
	 *
	 * @return	the label.
	 */
	protected JLabel getStatusLbl() {
		if (null == fStatusLbl)
			fStatusLbl = new JLabel();

		return fStatusLbl;
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Update the status message.
	 * 
	 * @param <code>status</code> the message.
	 */
	public synchronized void setStatus(String status) {
		String newStatus = getStatusLbl().getText();

		if ((status == newStatus) || status.equals(newStatus))
			return;

		getStatusLbl().setText(status);
		getStatusLbl().revalidate();
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////

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

		add(getStatusPnl(), BorderLayout.CENTER);
	}
}