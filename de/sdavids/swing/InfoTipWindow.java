/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.ToolTipManager;

/**
 * A window displaying a message.
 * <p>
 * This window is similar to a tooltip window but has a pink background.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	01-08-21
 */
public class InfoTipWindow extends TimedWindow {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The message to be displayed. */
	private String fMessage;

	/** Our preferred size. */
	private Dimension fPreferredSize;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a <code>InfoTipWindow</code> with no specified owner and an 
	 * empty message.
	 */
	public InfoTipWindow() {
		this("");
	}

	/**
	 * Creates a <code>InfoTipWindow</code> with no specified owner and the 
	 * given message.
	 * 
	 * @param	message		the message.
	 */
	public InfoTipWindow(String message) {
		super(ToolTipManager.sharedInstance().getDismissDelay());

		setMessage(message);

		setBackground(Color.pink);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the message to be displayed.
	 * <p>
	 * The message will be set to an empty string if
	 * <code>null == message</code>.
	 * 
	 * @param	message		the message.
	 */
	public void setMessage(String message) {
		if (null == message) {
			fMessage = "";
		} else if ((fMessage == message) || message.equals(fMessage)) {
			return;
		}

		fMessage = message;

		fPreferredSize = null; //insure it gets recalculated

		repaint();
	}

	/** 
	 * Paints the container.
	 *
	 * @param	g	the specified <code>Graphics</code> window.
	 */
	public void paint(Graphics g) {
		Dimension size = getSize();

		g.drawRect(0, 0, (size.width - 1), (size.height - 1));
		g.drawString(getMessage(), 2, (size.height - 4));
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Set the message to be displayed.
	 * 
	 * @param	message		the message.
	 */
	public String getMessage() {
		return fMessage;
	}

	/**
	 * Answer the preferred size of this component.
	 * 
	 * @return 	a <code>Dimension</code> object indicating this component's
	 * 				preferred size.
	 * @see java.awt.Component#getMinimumSize
	 * @see java.awt.LayoutManager
	 */
	public Dimension getPreferredSize() {
		if (null == fPreferredSize) {
			fPreferredSize = calculatePreferredSize();
		}

		return fPreferredSize;
	}

	//////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Calculate the preferred size for this component.
	 * 
	 * @return 	a <code>Dimension</code> object indicating this component's
	 * 				preferred size.
	 */
	private Dimension calculatePreferredSize() {
		FontMetrics metrics = getFontMetrics(getFont());

		int height = metrics.getHeight();
		int width = metrics.stringWidth(fMessage) + 1;

		return new Dimension(width, height);
	}
}