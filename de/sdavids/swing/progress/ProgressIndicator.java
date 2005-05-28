/* ===========================================================================

import javax.swing.JComponent;
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing.progress;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * A textfield showing a moving progress indicator.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-21
 */
public class ProgressIndicator extends JPanel {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ------------- */

	/** The the width of the indicator. */
	private static final int COLUMN_COUNT = 40;

	/** The moving indicator. */
	private static final String INDICATOR = "......";

	/** The moving indicator. */
	private static final int INDICATOR_LENGTH = INDICATOR.length();

	/** The number of animation &quot;frames&quot;. */
	private static final int ANIMATION_LENGTH =
		COLUMN_COUNT - INDICATOR_LENGTH + 1;

	/**
	 * The animation.
	 * <p>
	 * <code>ANIMATION[0]</code> is the rightmost postition of the indicator.
	 * <br>
	 * <code>ANIMATION[ANIMATION_LENGTH]</code> is the leftmost postition of the
	 * indicator.
	 */
	private static final String[] ANIMATION = new String[ANIMATION_LENGTH];

	/* ----------- Instance Attributes ---------- */

	/** Our timer. */
	private final Timer fTimer;

	/**
	 * The amount of time (in milliseconds) a particular animation frame is
	 * shown.
	 */
	private int fFrameShowTime;

	/** The textfield containing the moving indicator. */
	private JTextField fIndicator;

	/** The direction; either left-to-right or right-to-left */
	private boolean fRightToLeft;

	/** The current Animation frame. */
	private int fAnimationFrame;
	
	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor for ProgressIndicator with an <code>updateTime</code> of 
	 * 300 miliseconds.
	 */
	public ProgressIndicator() {
		this(300);
	}

	/**
	 * Constructor for ProgressIndicator.
	 * 
	 * @param	updateTime	the amount of time (in milliseconds) a particular
	 * 						animation frame is shown.
	 */
	public ProgressIndicator(int updateTime) {
		super(new BorderLayout());
		
		setFrameShowTime(updateTime);
		
		fTimer = new Timer(getFrameShowTime(), new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showNextAnimationFrame();
			}
		});

		initialize();
	}
				
	//////////////////////////////////////////////////////////////////////////	
	// WIDGETS
	//////////////////////////////////////////////////////////////////////////		

	/**
	 * Answer textfield showing the moving progress indicator.
	 * 
	 * @return	the textfield.
	 */
	protected JTextField getIndicator() {
		if (null == fIndicator) {
			fIndicator = new JTextField(INDICATOR);

			fIndicator.setColumns(COLUMN_COUNT);
			fIndicator.setEditable(false);
			fIndicator.setFont(Font.decode("Monospaced"));
		}

		return fIndicator;
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Start the animation.
	 */
	public synchronized void start() {
		if (!fTimer.isRunning()) {
			fRightToLeft = false;
			fAnimationFrame = -1;
			
			getIndicator().setText(ANIMATION[0]);			
			
			fTimer.start();
		}
	}

	/**
	 * Stop the animation.
	 */
	public synchronized void stop() {
		if (fTimer.isRunning())
			fTimer.stop();
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
		if (shouldShow) {
			start();
		} else {
			stop();
		}
		
		super.setVisible(shouldShow);
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Initialize the progress indicator.
	 * <p>
	 * Lays out all <code>Component</code>s and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		initAnimation();

		add(getIndicator());
	}

	//////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the amount of time (in milliseconds) a particular animation frame
	 * should be shown.
	 *
	 * @param	updateTime	the milliseconds.
	 * @throws	IllegalArgumentException	if <code>time &lt; 1</code>.
	 */	
	protected void setFrameShowTime(int time) {
		if (1 > time)
			throw new IllegalArgumentException(time + " < 1");
			
		fFrameShowTime = time;
	}
	
	/**
	 * Precompute the animation frames.
	 */
	protected void initAnimation() {
		char[] empty = new char[COLUMN_COUNT + 1];

		for (int i = COLUMN_COUNT; i >= 0; i--) {
			empty[i] = ' ';
		}

		String emptyStrg = new String(empty);

		for (int i = (ANIMATION.length - 1); i >= 0; i--) {
			ANIMATION[i] =
				new StringBuffer(emptyStrg)
					.replace(i, (i + 1 + INDICATOR_LENGTH), INDICATOR)
					.toString();
		}
	}

	/**
	 * Move to the next animation frame.
	 * <p>
	 * The animation moves from left to right until it &quot;hits&quot; the
	 * right bounds then moves right to left until it &quot;hits&quot; the
	 * left bounds and then it moves left to right again.
	 */
	protected void showNextAnimationFrame() {	
		if (fRightToLeft) {
			fAnimationFrame--;
	
			if (0 == fAnimationFrame) {
				fRightToLeft = false;
	
				return;
			}
		} else {
			fAnimationFrame++;
	
			if (ANIMATION_LENGTH == fAnimationFrame) {
				fRightToLeft = true;
	
				return;
			}
		}
	
		getIndicator().setText(ANIMATION[fAnimationFrame]);
	}
	
	/* ----------- Accessors -------------------- */

	/**
	 * Get the amount of time (in milliseconds) a particular animation frame
	 * should be shown.
	 *
	 * @param	the time in milliseconds.
	 */	
	protected int getFrameShowTime() {
		return fFrameShowTime;
	}			
}