/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.swing.progress;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JDialog;

import de.sdavids.swing.SwingWorker;

/**
 * A dialog displaying a progress.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	02-01-13
 */
public class NonModalProgressDialog extends JDialog {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/* ----------- Widgets ---------------------- */

	/** The progress panel. */
	private NonModalProgressPanel fProgressPanel;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a non-modal dialog with the specified application (parent).
	 *
	 * @param	owner		the owner frame.
	 * @param	config		the configuration.
	 */
	public NonModalProgressDialog(Frame owner, ResourceBundle config) {
		super(owner);

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
	protected NonModalProgressPanel getProgressPnl() {
		if (null == fProgressPanel) {
			fProgressPanel = new NonModalProgressPanel(getConfig());

			fProgressPanel
				.addPropertyChangeListener("workerStopped", new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					cancelAction();
				}
			});
		}

		return fProgressPanel;
	}

	//////////////////////////////////////////////////////////////////////////
	// ACTIONS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the <em>Cancel</em> button of the progress panel is pressed.
	 * <p>
	 * Fires a <code>PropertyChangeEvent</code> called <em>cancelPressed</em>.
	 */
	protected void cancelAction() {
		setVisible(false);

		dispose();

		firePropertyChange("cancelPressed", Boolean.FALSE, Boolean.TRUE);
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
		getProgressPnl().setMessage(message);
	}

	/**
	 * Set the <code>SwingWorker</code> to be interrupted if this dialog's
	 * <em>Cancel</em> button is pressed.
	 * <p>
	 * The given worker will be started by this method.
	 * <p>
	 * A former worker will get interrupted if it is not the same one.
	 * <p>
	 * <code>null == worker</code> indicates that we do not want to have a 
	 * worker assigned to us; this dialog will be hidden in that case.
	 * 
	 * @param	worker			the worker.
	 */
	public synchronized void setWorker(SwingWorker worker) {
		getProgressPnl().setWorker(worker);

		if (null == getWorker())
			setVisible(false);
	}

	/**
	 * Makes the dialog visible.
	 * <p>
	 * Puts the focus on the <em>Cancel<em> button of the progress panel.
	 */
	public void show() {
		super.show();

		getProgressPnl().focusOnCancelButton();
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
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				if (KeyEvent.VK_ESCAPE == evt.getKeyCode())
					cancelAction();
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				cancelAction();
			}
						
			public void windowOpened(WindowEvent evt) {
				getProgressPnl().focusOnCancelButton();
			}
		});

		setTitle(getConfig("dlg.progress.title"));
		setContentPane(getProgressPnl());

		pack();

		setResizable(false);
	}

	/**
	 * Set the dialog's configuration.
	 *
	 * @param	<code>config</code> the bundle.
	 */
	protected void setConfig(ResourceBundle config) {
		fConfig = config;
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
		return getProgressPnl().getWorker();
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