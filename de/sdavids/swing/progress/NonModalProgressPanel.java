/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.swing.progress;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.SwingWorker;

/**
 * A panel displaying a progress.
 * <p>
 * The panel starts it's animation if you call {@link setWorker(SwingWorker)
 * setWorker(SwingWorker)} with a non-<code>null</code> parameter; stop the
 * animation (work) prematurely by calling the same method with
 * <code>null</code> as its parameter.
 * <p>
 * This panel fires the following events:
 * <ul>
 * 	<li>a <code>PropertyChangeEvent</code> called <em>workerStopped</em> to
 * 		indicate that the user pressed has the <em>Cancel</em> button.</li>
 * </ul>
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	02-01-13
 */
public class NonModalProgressPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ------------- */

	/** The progress bar gets updated ever 300 miliseconds. */
	private static final int UPDATE_TIME = 300;

	/* ----------- Instance Attributes ---------- */

	/**
	 * The message to be displayed.
	 */
	private String fMessage;

	/**
	 * The worker we want to be able to interrupt.
	 */
	private SwingWorker fWorker;

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/* ----------- Widgets ---------------------- */

	/** The close button. */
	private JButton fCancelBtn;

	/** The label displaying the message. */
	private JLabel fMessageLbl;

	/** The progress bar. */
	private ProgressIndicator fProgressIndicator;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Creates the panel with the given configuration.
	 *
	 * @param	config		the configuration.
	 */
	public NonModalProgressPanel(ResourceBundle config) {
		super();

		setConfig(config);

		initialize();
	}

	//////////////////////////////////////////////////////////////////////////	
	// WIDGETS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the progress panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getProgressPnl() {
		JPanel result = new JPanel(new BorderLayout(0, 0));

		result.add(getProgressIndicator(), BorderLayout.NORTH);

		return result;
	}

	/**
	 * Answer the <em>Cancel</em> button panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getCancelBtnPnl() {
		JPanel result = new JPanel(new BorderLayout(0, 0));

		result.add(getCancelBtn(), BorderLayout.EAST);

		return result;
	}

	/**
	 * Answer the progress bar.
	 * 
	 * @return	the bar.
	 */
	protected ProgressIndicator getProgressIndicator() {
		if (null == fProgressIndicator) {
			fProgressIndicator = SwingCreator.newProgressIndicator();
		}

		return fProgressIndicator;
	}

	/**
	 * Answer the message panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getMessagePnl() {
		JPanel result = new JPanel(new BorderLayout(0, 0));

		result.add(getMessageLbl(), BorderLayout.NORTH);

		return result;
	}

	/**
	 * Answer the label displaying the message.
	 * 
	 * @return	the label.
	 */
	protected JLabel getMessageLbl() {
		if (null == fMessageLbl) {
			fMessageLbl = new JLabel();
		}

		return fMessageLbl;
	}

	/**
	 * Answer the Cancel button.
	 * 
	 * @return	the button.
	 */
	protected JButton getCancelBtn() {
		if (null == fCancelBtn) {
			fCancelBtn =
				SwingCreator.newJButton("btn.cancel", getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					cancelAction();
				}
			});
		}

		return fCancelBtn;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the <em>Cancel</em> button is pressed.
	 * <p>
	 * Fires a <code>PropertyChangeEvent</code> called <em>workerStopped</em>.
	 */
	protected void cancelAction() {
		stopWorker();

		firePropertyChange("workerStopped", false, true);
	}

	//////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the message to be displayed by this dialog.
	 * 
	 * @param	message			the message.
	 * @throws	IllegalArgumentException		if <code>message</code> is
	 * 											<code>null</code>.
	 */
	public synchronized void setMessage(String message) {
		if (null == message)
			throw new IllegalArgumentException("login null");

		if (message.equals(fMessage))
			return;

		fMessage = message;

		getMessageLbl().setText(message);
	}

	/**
	 * Set the <code>SwingWorker</code> to be interrupted if this dialog's
	 * <em>Cancel</em> button is pressed.
	 * <p>
	 * The given worker will be started by this method.
	 * <p>
	 * A former worker will get stopped if it is not the same one.
	 * <p>
	 * <code>null == worker</code> indicates that we do not want to have a 
	 * worker assigned to us; the animation will not be started as well.
	 * 
	 * @param	worker			the worker.
	 */
	public synchronized void setWorker(SwingWorker worker) {
		if (fWorker == worker)
			return;

		stopWorker();

		fWorker = worker;

		if (fWorker == null)
			return;

		getProgressIndicator().start();

		fWorker.start();
	}

	/**
	 * Answer if our worker is working.
	 * 
	 * @return		<code>true</code> if we have one.
	 */
	public boolean isWorking() {
		return (null != fWorker);
	}

	/**
	 * Shows or hides this window depending on the value of parameter
	 * <code>shouldShow</code>.
	 * <p>
	 * The animation will be stopped while the panel is not shown.
	 * 
	 * @param shouldShow		if <code>true</code>, shows this component; 
	 * 							otherwise, hides this component.
	 * 
	 * @see #isVisible
	 */
	public void setVisible(boolean shouldShow) {
		super.setVisible(shouldShow);

		if (shouldShow && isWorking()) {
			getProgressIndicator().start();
		} else {
			getProgressIndicator().stop();
		}
	}

	/**
	 * Put the focus on the <em>Cancel</em> button.
	 */
	public void focusOnCancelButton() {
		if (!getCancelBtn().hasFocus()) {
			if (!getCancelBtn().isRequestFocusEnabled())
				getCancelBtn().setRequestFocusEnabled(true);

			getCancelBtn().requestFocus();
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the dialog.
	 * <p>
	 * Lays out all <code>Component</code>s and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		setLayout(new BorderLayout(0, 16));
		setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		add(getMessagePnl(), BorderLayout.NORTH);
		add(getProgressPnl(), BorderLayout.CENTER);
		add(getCancelBtnPnl(), BorderLayout.SOUTH);
	}

	/**
	 * Set the dialog's configuration.
	 *
	 * @param	<code>config</code> the bundle.
	 */
	protected void setConfig(ResourceBundle config) {
		fConfig = config;
	}

	/**
	 * Stops our worker.
	 */
	protected synchronized void stopWorker() {
		if (!isWorking())
			return;

		getWorker().stop();

		getProgressIndicator().stop();
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the configuration.
	 *
	 * @return	the configuration.
	 */
	protected ResourceBundle getConfig() {
		return fConfig;
	}

	/**
	 * Answer the <code>SwingWorker</code>.
	 *
	 * @return	the worker.
	 */
	protected SwingWorker getWorker() {
		return fWorker;
	}

	/**
	 * Answer the string resource with the given key.
	 * 
	 * @return	the resource.
	 */
	protected String getConfig(String key) {
		return getConfig().getString(key);
	}
}