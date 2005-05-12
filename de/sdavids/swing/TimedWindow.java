/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2001, 2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JWindow;
import javax.swing.Timer;

/**
 * A window which hides itself after a certain amount of time.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	01-08-21
 */
public class TimedWindow
			extends JWindow {
	
	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Instance Attributes ---------- */
	
	/** Our timer.*/			    
    private final Timer fTimer;
    
    //////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a <code>InfoTipWindow</code> with no specified owner and an 
	 * the specified run time.
	 * 
     * @param runTime		the number of milliseconds this window should be
     * 						shown each time  * Call {@link #setVisible(boolean)
     * 						setVisible(boolean)} with <code>true</code> for it's
     * 						parameter is called.
	 */	
    public TimedWindow(int runTime) {
    	fTimer = new Timer(runTime, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
				
			    dispose();
			}
		});
    }

    /**
     * Shows or hides this window depending on the value of parameter
     * <code>shouldShowb</code>.
     * <p>
     * This window is shown for a specific amount of time (determined by the
     * <code>runTime</code> parameter of the constructor) if
     * <code>true == shouldShow</code>; afterwards this window gets disposed.
     * 
     * @param shouldShow		if <code>true</code>, shows this component; 
     * 							otherwise, hides this component.
     * 
     * @see #isVisible
     */	   	    
    public void setVisible(boolean shouldShow) {
		super.setVisible(shouldShow);

		if (shouldShow) {
		    fTimer.start();
		} else {
		    fTimer.stop();
		}
    }
}

