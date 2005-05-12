package de.sdavids.swing.dialog;

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
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.sdavids.swing.SwingCreator;

/**
 */
public class ConfirmationDialog extends JDialog {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/** Remember which button the user has pressed. */
	private ButtonConstants fTerminatingButton;
	
	/* ----------- Widgets ---------- */

	/** Reference to the Cancel button.*/
	private JButton fCancelBtn;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a modal dialog with the specified configuration, name, and
	 * the specified owner frame.
	 *
	 * @param	<code>owner</code> the <code>Frame</code> from which
	 * 			the dialog is displayed.
	 * @param	<code>config</code> the configuration.
	 * @param	<code>id</code> the ID of the questionaire.
	 */
	public ConfirmationDialog(Frame owner, ResourceBundle config, String name) {
		super(owner);

		setConfig(config);
		setName(name);
		setTitle(getResource(".title"));
		setModal(true);

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Makes the dialog visible.
	 * 
	 * Puts the focus on the &quot;Cancel&quot; button.
	 */
	public void show() {
		fTerminatingButton = ButtonConstants.CANCEL;
				
		if (!getCancelBtn().hasFocus()) {
			if (!getCancelBtn().isRequestFocusEnabled()) {
				getCancelBtn().setRequestFocusEnabled(true);
			}

			getCancelBtn().requestFocus();
		}
		
		super.show();		
	}
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer the termination button.
	 *
	 * @return	the button.
	 */
	public ButtonConstants getTerminatingButton() {
		return fTerminatingButton;
	}

	/////////////////////////////////////////////////////////////////////////////
	// WIDGETS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the OK button.
	 * 
	 * @return	the button.
	 */
	protected JButton getOKBtn() {
			JButton fOKBtn = //non-I18N-NLS
	SwingCreator.newJButton("btn.ok", getConfig(), new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okAction();
			}
		});

		return fOKBtn;
	}

	/**
	 * Answer the Cancel button.
	 * 
	 * @return	the button.
	 */
	protected JButton getCancelBtn() {
		if (null == fCancelBtn) {
				fCancelBtn = //non-I18N-NLS
	SwingCreator.newJButton("btn.cancel", getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					cancelAction();
				}
			});
		}

		return fCancelBtn;
	}

	/**
	 * Answer the content panel.
	 * 
	 * The content panel contains the ID text field and an accompaning label.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		JPanel result = new JPanel(new BorderLayout());

		result.add(new JLabel(getResource(".msg")), BorderLayout.NORTH);

		return result;
	}

	/**
	 * Answer the button panel.
	 * 
	 * The button panel contains the OK and Cancel buttons.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getButtonPnl() {
		JPanel result = new JPanel(new BorderLayout());
		
		JPanel pnl = new JPanel(new GridLayout(1, 2, 5, 0));

		pnl.add(getOKBtn());
		pnl.add(getCancelBtn());

		result.add(pnl, BorderLayout.EAST);
		
		return result;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the OK button is pressed.
	 * 
	 * Updates the <em>id</em>.
	 */
	protected void okAction() {
		fTerminatingButton = ButtonConstants.OK;

		dispose();
	}

	/**
	 * Invoked when the cancel button is pressed.
	 */
	protected void cancelAction() {
		dispose();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the dialog.
	 * 
	 * Lays out all <code>Components</code> and registers needed
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
				fTerminatingButton = ButtonConstants.CANCEL;
						
				if (!getCancelBtn().hasFocus()) {
					if (!getCancelBtn().isRequestFocusEnabled()) {
						getCancelBtn().setRequestFocusEnabled(true);
					}
		
					getCancelBtn().requestFocus();
				}				
			}
		});

		JPanel pnl = new JPanel(new BorderLayout(0, 17));

		pnl.setBorder(
			BorderFactory
				.createCompoundBorder(
					BorderFactory.createLineBorder(Color.red),
					BorderFactory
				.createEmptyBorder(12, 12, 12, 12)));

		pnl.add(getContentPnl(), BorderLayout.NORTH);
		pnl.add(getButtonPnl(), BorderLayout.SOUTH);

		setContentPane(pnl);

		pack();

		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
	 * Answer the resource bundle containing the configuration.
	 * 
	 * @return    the bundle.
	 */
	protected ResourceBundle getConfig() {
		return fConfig;
	}

	/**
	 * Answer the string resource with the given key.
	 * 
	 * @return    the resource.
	 */
	protected String getResource(String key) {
		return getConfig().getString(getName() + key);
	}
}