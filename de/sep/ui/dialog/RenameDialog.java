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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.dialog.InfoTipDialog;
import de.sep.logic.QuestionaireFactory2;
import de.sep.ui.Questionaire;

/**
 * A dialog for renaming a given <code>Questionaire</code>.
 */
public class RenameDialog extends InfoTipDialog {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Bean properties ---------- */

	/**
	 * The new title of the questionaire.
	 * 
	 * R property.
	 */
	private String fNewTitle;
		
	/* ----------- Instance Attributes ---------- */

	/** A reference to the application. */
	private Questionaire fApp;

	/* ----------- Widgets ---------- */

	/** Reference to the OK button.*/
	private JButton fOKBtn;

	/** Reference to the title textfield.*/
	private JTextField fTitleTF;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a non-modal dialog with a reference to the application.
	 *
	 * @param	<code>app<code> a reference to the application.
	 */
	public RenameDialog(Questionaire app) {
		super(app);

		setApp(app);

		initialize();
	}

	//////////////////////////////////////////////////////////////////////////////
	// BOUND PROPERTIES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the questionaire's title.
	 *
	 * @return	the title.
	 */
	public String getQuestionaireTitle() {
		return fNewTitle;
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
				fOKBtn = //non-I18N-NLS
	SwingCreator.newJButton("btn.ok", getApp().getConfig(), new ActionListener() {
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
			JButton result = //non-I18N-NLS
	SwingCreator.newJButton("btn.cancel", getApp().getConfig(), new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelAction();
			}
		});

		return result;
	}

	/**
	 * Answer the title textfield.
	 * 
	 * @return	the textfield.
	 */
	protected JTextField getTitleTF() {
		if (null == fTitleTF) {
				fTitleTF = //non-I18N-NLS
	SwingCreator
		.newJTextField(
			"dlg.renameQuestionaire.idTF",
			getApp().getConfig(),
			new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					okAction();
				}
			}, new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredAction(evt);
				}
			});
		}

		return fTitleTF;
	}

	/**
	 * Answer the content panel.
	 * 
	 * The content panel contains the ID text field and an accompaning label.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		JPanel result = new JPanel(new GridLayout(2, 1));

		result.add(new JLabel(getResource("dlg.renameQuestionaire.msg")));
		//non-I18N-NLS
		result.add(getTitleTF());

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
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Makes the Dialog visible.
	 * 
	 * Puts the focus on the &quot;ID TF&quot; button.
	 */
	public void show() {
		if (!getTitleTF().hasFocus()) {
			if (!getTitleTF().isRequestFocusEnabled())
				getTitleTF().setRequestFocusEnabled(true);

			getTitleTF().requestFocus();
		}

		super.show();
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the OK button is pressed.
	 * 
	 * Updates the <em>title</em>.
	 */
	protected void okAction() {
		getTitleTF().getInputContext().endComposition();
		
		String newTitle = getTitleTF().getText().trim();
		
		if (!getQuestionaireTitle().equals(newTitle))
			setQuestionaireTitle(newTitle);

		dispose();
	}

	/**
	 * Invoked when the cancel button is pressed.
	 */
	protected void cancelAction() {
		setQuestionaireTitle(getApp().getCurrentDoc().getTitle());

		dispose();
	}

	/**
	 * Invoked when a key is entered.
	 * 
	 * The OK button will be disabled and the textfields foreground color
	 * will be set to red if the user enters nothing or illegal characters.
	 * 
	 * @param 	<code>evt</code> the <code>KeyEvent</code> we want to
	 * 			respond to.
	 */
	protected void keyEnteredAction(KeyEvent evt) {
		if (evt.isActionKey() && (evt.VK_BACK_SPACE != evt.getKeyCode()))
			return;

		String id = getTitleTF().getText().trim();

		boolean legal = QuestionaireFactory2.isLegalQuestionaireTitle(id);

		if (!legal) {
			if ("".equals(getTitleTF().getText())) {
				showInfoTip(getTitleTF(), getResource("dlg.renameQuestionaire.idTF.infotip"));

				getToolkit().beep();
			} else if (!"".equals(id)) {
				showInfoTip(getTitleTF(), getTitleTF().getToolTipText());

				getToolkit().beep();
			} else {
				hideInfoTip();
			}
		} else {
			hideInfoTip();
		}

		getOKBtn().setEnabled(legal);

		getTitleTF().setForeground((legal) ? Color.black : Color.red);
	}

	/**
	 * Invoked when the title property of the current document changes.
	 */
	protected void updateTitleAction() {
		String[] values = { getApp().getCurrentDoc().getTitle() };

		String title = getResource("dlg.renameQuestionaire.title");

		title = SwingCreator.replacePlaceHolders(title, values);

		setTitle(title);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the questionaire's title.
	 *
	 * @param	<code>title</code> the title.
	 * @see	#de.sep.model.questionaireQuestionaireFactory2.isLegalQuestionaireID.
	 */
	protected void setQuestionaireTitle(String title)
		throws IllegalArgumentException {
			
		getTitleTF().setText(title);

		boolean legal = QuestionaireFactory2.isLegalQuestionaireTitle(title);
		
		getOKBtn().setEnabled(legal);
		
		getTitleTF().setForeground((legal) ? Color.black : Color.red);
		
		fNewTitle = title;
	}

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
				if (!getTitleTF().hasFocus()) {
					if (!getTitleTF().isRequestFocusEnabled())
						getTitleTF().setRequestFocusEnabled(true);

					getTitleTF().requestFocus();
				}
			}
		});

		setQuestionaireTitle(getApp().getCurrentDoc().getTitle());
		
		getApp().getCurrentDoc().addPropertyChangeListener("title", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				updateTitleAction();
			}
		});

		updateTitleAction();
		
		JPanel pnl = new JPanel(new BorderLayout(0, 17));

		pnl.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		pnl.add(getContentPnl(), BorderLayout.NORTH);
		pnl.add(getButtonPnl(), BorderLayout.SOUTH);

		setContentPane(pnl);
		
		pack();
		
		setModal(false);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setApp(Questionaire app) {
		fApp = app;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the reference to the application.
	 *
	 * @return	the reference.
	 */
	protected Questionaire getApp() {
		return fApp;
	}
	
	/**
	 * Answer the string resource with the given key.
	 * 
	 * @return	the resource.
	 */
	protected String getResource(String key) {
		return getApp().getConfig(key);
	}
}