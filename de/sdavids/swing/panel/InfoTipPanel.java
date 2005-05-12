/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing.panel;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import de.sdavids.swing.SwingUtils;
import de.sdavids.swing.InfoTipWindow;

/**
 * A panel supporting the display of an <code>InfoTipWindow</code> via the
 * {@link #showInfoTip(Component, String) showInfoTip(Component, String)}
 * method.
 * 
 * @see		de.sdavids.login.ILogin
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	01-08-21
 */
public class InfoTipPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** Our <code>InfoTipWindow</code>. */
	private InfoTipWindow fInfoTipWindow;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Create a new <code>InfoTipPanel</code> with a double buffer and a 
	 * <code>FlowLayout</code>.
	 */
	public InfoTipPanel() {
		super();
	}

	/**
	 * Create a new <code>InfoTipPanel</code> with <code>FlowLayout</code> and
	 * the specified buffering strategy.
	 * <p>
	 * If <code>isDoubleBuffered</code> is <code>true</code>, the
	 * <code>InfoTipPanel</code> will use a double buffer.
	 *
	 * @param	isDoubleBuffered	a boolean, <code>true</code> for
	 * 								double-buffering, which uses additional
	 * 								memory space to achieve fast, flicker-free 
	 *        						updates.
	 */
	public InfoTipPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * Creates a new double buffered <code>InfoTipPanel</code> with the
	 * specified layout manager and buffering strategy.
	 *
	 * @param	layout				the <code>LayoutManager</code> to use.
	 */
	public InfoTipPanel(LayoutManager layout) {
		super(layout);
	}

	/**
	 * Creates a new <code>InfoTipPanel</code> with the specified layout manager
	 * and buffering strategy.
	 *
	 * @param	layout				the <code>LayoutManager</code> to use.
	 * @param	isDoubleBuffered	a boolean, <code>true</code> for
	 * 								double-buffering, which uses additional
	 * 								memory space to achieve fast, flicker-free 
	 *        						updates.
	 */
	public InfoTipPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Show the given message below the given <code>Component</code> in a
	 * <code>InfoTipWindow</code>.
	 *
	 * @param	comp			the message is displayed below this
	 * 							<code>Component</code>.
	 * @param	message			the message to be displayed.
	 */
	protected void showInfoTip(Component comp, String message) {
		InfoTipWindow info = getInfoTipWindow();
		
		boolean sameText = info.getMessage().equals(message);

		if (info.isVisible() && sameText)
			return;

		if (!sameText)
			info.setMessage(message);

		SwingUtils.showBelow(comp, info);
	}

	/**
	 * Hide our <code>InfoTipWindow</code>.
	 */
	protected void hideInfoTip() {
		getInfoTipWindow().setVisible(false);
	}

	//////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Accessors -------------------- */
	
	/**
	 * Answer our <code>InfoTipWindow</code>.
	 * 
	 * @return		the <code>InfoTipWindow</code>.
	 */	
	private InfoTipWindow getInfoTipWindow() {
		if (null == fInfoTipWindow) {
			fInfoTipWindow = new InfoTipWindow();
			
		}

		return fInfoTipWindow;
	}
}