package de.sep.ui.dialog;

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
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.dataobjects.person.PersonFactory;
import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.dialog.InfoTipDialog;
import de.sdavids.util.StringUtils;

/**
 * A dialog for changing the author of a given <code>Questionaire</code>.
 */
public class ChangeAuthorDialog extends InfoTipDialog {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/**
	 * The author of the questionaire.
	 */
	private IPerson fAuthor;

	/* ----------- Widgets ---------- */

	/** Reference to the OK button.*/
	private JButton fOKBtn;

	/** Reference to the first name textfield.*/
	private JTextField fFirstNameTF;

	/** Reference to the last name textfield.*/
	private JTextField fLastNameTF;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a non-modal dialog with the specified owner frame, configuration,
	 * and author.
	 *
	 * @param	<code>owner</code> the <code>Frame</code> from which
	 * 			the dialog is displayed.
	 * @param	<code>config</code> the configuration.
	 * @param	<code>author</code> the author.
	 */
	public ChangeAuthorDialog(
		Frame owner,
		ResourceBundle config,
		IPerson author) {

		super(owner);

		setConfig(config);
		setAuthor(author);

		initialize();
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
		if (null == fOKBtn) {
			fOKBtn =
				SwingCreator.newJButton("btn.ok", getConfig(), new ActionListener() {
					//non-I18N-NLS
	public void actionPerformed(ActionEvent evt) {
					okAction();
				}
			});
		}

		return fOKBtn;
	}

	/**
	 * Answer the Cancel button.
	 * 
	 * @return	the button.
	 */
	protected JButton getCancelBtn() {
		JButton result =
			SwingCreator.newJButton("btn.cancel", getConfig(), new ActionListener() {
				//non-I18N-NLS
	public void actionPerformed(ActionEvent evt) {
				cancelAction();
			}
		});

		return result;
	}

	/**
	 * Answer the last name's textfield.
	 * 
	 * @return	the textfield.
	 */
	protected JTextField getLastNameTF() {
		if (null == fLastNameTF) {
				fLastNameTF =
					SwingCreator.newJTextField("dlg.changeAuthor.lastNameTF", //non-I18N-NLS
	getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					okAction();
				}
			}, new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredAction(evt, getLastNameTF());
				}
			});
		}

		return fLastNameTF;
	}

	/**
	 * Answer the first name's textfield.
	 * 
	 * @return	the textfield.
	 */
	protected JTextField getFirstNameTF() {
		if (null == fFirstNameTF) {
				fFirstNameTF =
					SwingCreator.newJTextField("dlg.changeAuthor.firstNameTF", //non-I18N-NLS
	getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					okAction();
				}
			}, new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredAction(evt, getFirstNameTF());
				}
			});
		}

		return fFirstNameTF;
	}

	/**
	 * Answer the content panel.
	 * 
	 * The content panel contains the first and last name texfields and 
	 * accompaning labels.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		JPanel result = new JPanel(new BorderLayout(12, 0));

		result.add(getLabelPnl(), BorderLayout.WEST);
		result.add(getValuePnl(), BorderLayout.CENTER);

		return result;
	}

	/**
	 * Answer the label panel.
	 * 
	 * The content panel contains labels for the first and last name texfields.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getLabelPnl() {
		JPanel result = new JPanel(new GridLayout(2, 1, 0, 12));

		result.add(new JLabel(getResource("dlg.changeAuthor.firstName")));
		//non-I18N-NLS
		result.add(new JLabel(getResource("dlg.changeAuthor.lastName")));
		//non-I18N-NLS

		return result;
	}

	/**
	 * Answer the value panel.
	 * 
	 * The value panel contains the first and last name texfields.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getValuePnl() {
		JPanel result = new JPanel(new GridLayout(2, 1, 0, 12));

		result.add(getFirstNameTF());
		result.add(getLastNameTF());

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
		JPanel pnl = new JPanel(new GridLayout(1, 2, 5, 0));

		pnl.add(getOKBtn());
		pnl.add(getCancelBtn());

		JPanel result = new JPanel(new BorderLayout());

		result.add(pnl, BorderLayout.EAST);

		return result;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the OK button is pressed.
	 * 
	 * Updates the <em>author</em> <code>Person</code> with the new values.
	 * 
	 * If both textfields are empty the <em>author</em> is set to a <code>NullPerson</code>.</li>
	 * 
	 * If the author is a <code>NullPerson</code> it is exchanged with a new <code>Person</code>.
	 *
	 * @see	de.sdavids.dataobjects.common.Person.
	 * @see	de.sdavids.dataobjects.common.NullPerson. 
	 */
	protected void okAction() {
		getFirstNameTF().getInputContext().endComposition();
		getLastNameTF().getInputContext().endComposition();
		
		String firstName = getFirstNameTF().getText().trim();
		String lastName = getLastNameTF().getText().trim();

		if (("".equals(firstName)) && ("".equals(lastName))) {
			setAuthor(PersonFactory.getNullPerson());
		} else {
			if (getAuthor().isNull()) {
				setAuthor(PersonFactory.create(firstName, lastName));
			} else {
				getAuthor().setFirstName(firstName);
				getAuthor().setLastName(lastName);
			}
		}

		dispose();
	}

	/**
	 * Invoked when the cancel button is pressed.
	 */
	protected void cancelAction() {
		getFirstNameTF().setText(getAuthor().getFirstName());
		getFirstNameTF().setForeground(Color.black);

		getLastNameTF().setText(getAuthor().getLastName());
		getLastNameTF().setForeground(Color.black);

		getOKBtn().setEnabled(true);

		dispose();
	}

	/**
	 * Invoked when a key is entered.
	 * 
	 * The OK button will be disabled and the textfields foreground color
	 * will be set to red if the user enters illegal characters; also an
	 * info tip will be displayed.
	 *  
	 * @param 	<code>evt</code> the <code>KeyEvent</code> we want to
	 * 			respond to.
	 */
	protected void keyEnteredAction(KeyEvent evt, JTextField tf) {
		if (evt.isActionKey())
			return;

		boolean legal = isValidData(tf.getText());

		if (!legal) {
			showInfoTip(tf, tf.getToolTipText());

			getToolkit().beep();
		} else {
			hideInfoTip();
		}

		getOKBtn().setEnabled(isValidData());

		tf.setForeground((legal) ? Color.black : Color.red);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the questionaire's author.
	 *
	 * @param	<code>author</code> the author.
	 */
	public void setAuthor(IPerson author) {
		getFirstNameTF().setText(author.getFirstName());
		getLastNameTF().setText(author.getLastName());

		fAuthor = author;
	}

	/**
	 * Makes the Dialog visible.
	 * 
	 * Puts the focus on the &quot;first name TF&quot; button.
	 */
	public void show() {
		getOKBtn().setEnabled(isValidData());

		if (!getFirstNameTF().hasFocus()) {
			if (!getFirstNameTF().isRequestFocusEnabled())
				getFirstNameTF().setRequestFocusEnabled(true);

			getFirstNameTF().requestFocus();
		}

		super.show();
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the questionaire's author.
	 *
	 * @return	the author.
	 */
	public IPerson getAuthor() {
		return fAuthor;
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
	protected void setConfig(ResourceBundle config) {
		fConfig = config;
	}

	/**
	 * Initialize the dialog.
	 * 
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
				if (!getFirstNameTF().hasFocus()) {
					if (!getFirstNameTF().isRequestFocusEnabled())
						getFirstNameTF().setRequestFocusEnabled(true);

					getFirstNameTF().requestFocus();
				}
			}
		});

		JPanel pnl = new JPanel(new BorderLayout(0, 17));

		pnl.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		pnl.add(getContentPnl(), BorderLayout.NORTH);
		pnl.add(getButtonPnl(), BorderLayout.SOUTH);

		setContentPane(pnl);

		setTitle(getResource("dlg.changeAuthor.title")); //non-I18N-NLS
		setModal(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		pack();

		setResizable(false);
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer if all data the user entered is valid.
	 * 
	 * @return    <code>true</code> if it is valid.
	 */
	protected boolean isValidData() {
		return isValidData(getFirstNameTF().getText()) && isValidData(getLastNameTF().getText());
	}

	/**
	 * Answer if the data the user entered is valid.
	 * 
	 * @param	entry		the data to be evaluated.
	 * @return    <code>true</code> if it is valid.
	 */
	protected boolean isValidData(String entry) {
		return StringUtils.hasOnlyLettersAndWS(entry)	;
	}
	
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
		return getConfig().getString(key);
	}
}