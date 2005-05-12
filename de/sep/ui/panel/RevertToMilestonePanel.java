package de.sep.ui.panel;

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
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import de.sdavids.swing.OrientationConstants;
import de.sdavids.swing.SwingCreator;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;
import de.sep.ui.actions.IQuestionaireActions;
import de.sep.ui.html.HTMLPreviewPanel;

/**
 *
 */
public class RevertToMilestonePanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** A reference to the application. */
	private Questionaire fApp;

	/** The the current doc. */
	private IQuestionaire fDoc;

	/** The current orientation. */
	private OrientationConstants fOrientation;

	/** Whether the HTMLPreview should be shown or not. */
	private boolean fShowHTMLPreview;

	/** The view state before the showing this panel. */
	private boolean fWasInHTMLPreview;

	/* ----------- Widgets ---------- */

	/** Reference to the OK button.*/
	private JButton fOKBtn;

	/** Reference to the flippable panel.*/
	private JPanel fFlipPanel;

	/** The panel containing the version table.*/
	private JPanel fMilestonePnl;

	/** Reference to the flippable panel.*/
	private JScrollPane fHTMLPreviewPnl;

	/** The popup menu. */
	private JPopupMenu fPopupMenu;

	/////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a non-modal dialog with the specified configuration, document, and
	 * the specified owner frame.
	 *
	 * @param	<code>doc</code> the questionaire model.
	 * @param	<code>app<code> a reference to the application.
	 * @param	<code>orientation</code> the initial orientation.
	 */
	public RevertToMilestonePanel(
		IQuestionaire doc,
		Questionaire app,
		OrientationConstants orientation) {
		super();

		setApp(app);
		setDocument(doc);
		setOrientation(orientation);

		fShowHTMLPreview = false;

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
				fOKBtn = //non-I18N-NLS
	SwingCreator
		.newJButton("btn.ok", getApp().getConfig(), new ActionListener() {
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
	SwingCreator
		.newJButton("btn.cancel", getApp().getConfig(), new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelAction();
			}
		});

		return result;
	}

	/**
	 * Answer the content panel.
	 * 
	 * The content panel contains the ID text field and an accompaning label.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getFlipPnl() {
		if (null == fFlipPanel) {
			fFlipPanel = new JPanel(new BorderLayout(12, 12));

			fFlipPanel.add(getHTMLPreviewPnl(), BorderLayout.CENTER);

			if (isNorthSouth()) {
				fFlipPanel.add(getMilestonePnl(), BorderLayout.NORTH);
			} else {
				fFlipPanel.add(getMilestonePnl(), BorderLayout.WEST);
			}
		}

		return fFlipPanel;
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

	/**
	 * Answer the version panel.
	 *
	 * @return	the panel.
	 */
	protected JPanel getMilestonePnl() {
		if (null == fMilestonePnl) {
			fMilestonePnl = new JPanel(new BorderLayout());

			//JScrollPane sp = new JScrollPane();

			JPanel pnl = new JPanel(new BorderLayout());

			pnl.add(new JLabel("Version"), BorderLayout.NORTH);
			pnl.setBackground(Color.red);

			//sp.add(pnl);

			fMilestonePnl.add(pnl, BorderLayout.NORTH);
		}

		return fMilestonePnl;
	}

	/**
	 * Answer the HTML preview panel.
	 *
	 * @return	the panel.
	 */
	protected JScrollPane getHTMLPreviewPnl() {
		if (null == fHTMLPreviewPnl) {
			HTMLPreviewPanel pnl =
				new HTMLPreviewPanel(getDocument(), getApp().getHTMLFactory());

			pnl.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					showPopupMenu(evt);
				}

				public void mousePressed(MouseEvent evt) {
					showPopupMenu(evt);
				}

				public void mouseReleased(MouseEvent evt) {
					showPopupMenu(evt);
				}
			});

			fHTMLPreviewPnl = new JScrollPane(pnl);
		}

		return fHTMLPreviewPnl;
	}

	/**
	 * Answer the popup menu.
	 *
	 * @return	the popup menu.
	 */
	protected JPopupMenu getPopupMenu() {
		if (null == fPopupMenu) {
			fPopupMenu = new JPopupMenu();
			fPopupMenu.add(
				SwingCreator.newJCheckBoxMenuItem(
					getApp().actionForName(IQuestionaireActions.ACTION_HTML_PREVIEW)));
			fPopupMenu.add(
				SwingCreator.newJMenuItem(
					getApp().actionForName(IQuestionaireActions.ACTION_FLIP_ORIENTATION)));
		}

		return fPopupMenu;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when the questionaire model has changed.
	 */
	protected void questionaireChangedAction(PropertyChangeEvent evt) {
		//String propName = evt.getPropertyName();

		if (isVisible())
			revalidate();
	}

	/**
	 * Invoked when the OK button is pressed.
	 * 
	 * Updates the <em>id</em>.
	 */
	protected void okAction() {
		if (true /*isVersionSelected()*/
			) {
			//revert

			changeToPreviousViewAction();
		} else {
			cancelAction();
		}
	}

	/**
	 * Invoked when the cancel button is pressed or the panel is hidden.
	 */
	protected void cancelAction() {
		//setNullSelection();

		changeToPreviousViewAction();
	}

	protected void changeToPreviousViewAction() {
		getApp().showDocumentPane();
		//sollte sich den vorherigen status irgendwo merken

		getApp().enableAction(IQuestionaireActions.ACTION_SNAPSHOT_COPY);

		getApp().deselectAction(IQuestionaireActions.ACTION_HTML_PREVIEW);
		//		
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Show the HTML indicator button.
	 */
	public void showHTMLPreview() {
		fShowHTMLPreview = true;

		if (!getApp().isActionSelected(IQuestionaireActions.ACTION_HTML_PREVIEW))
			getApp().selectAction(IQuestionaireActions.ACTION_HTML_PREVIEW);

		getHTMLPreviewPnl().setVisible(true);

		getApp().enableAction(IQuestionaireActions.ACTION_FLIP_ORIENTATION);

		relayoutMilestonePanel();
	}

	/**
	 * Hide the HTML indicator button.
	 */
	public void hideHTMLPreview() {
		fShowHTMLPreview = false;
		if (getApp().isActionSelected(IQuestionaireActions.ACTION_HTML_PREVIEW))
			getApp().deselectAction(IQuestionaireActions.ACTION_HTML_PREVIEW);

		getHTMLPreviewPnl().setVisible(false);

		getApp().disableAction(IQuestionaireActions.ACTION_FLIP_ORIENTATION);

		relayoutMilestonePanel();
	}

	/**
	 * Makes the panel visible.
	 * 
	 * Puts the focus on the &quot;Cancel&quot; button.
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			fWasInHTMLPreview =
				getApp().isActionSelected(IQuestionaireActions.ACTION_HTML_PREVIEW);

			if (!getCancelBtn().hasFocus()) {
				if (!getCancelBtn().isRequestFocusEnabled())
					getCancelBtn().setRequestFocusEnabled(true);

				getCancelBtn().requestFocus();
			}

			if (fShowHTMLPreview) {
				showHTMLPreview();
			} else {
				hideHTMLPreview();
			}
		}

		super.setVisible(visible);
	}

	/**
	 * Set the document.
	 *
	 * @param	<code>doc</code> the document.
	 * @throws <code>IllegalArgumentException</code> if <code>doc</code>
	 * 			is <code>nul.</code>.
	 */
	public void setDocument(IQuestionaire doc) throws IllegalArgumentException {
		if (null == doc)
			throw new IllegalArgumentException("doc null"); //Non-I18N-NLS

		fDoc = doc;

		fDoc.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				questionaireChangedAction(evt);
			}
		});
	}

	/**
	 * Flip from East-West-orientation to North-South-orientation and
	 * vice versa.
	 */
	public void flipOrientation() {
		setOrientation(
			isNorthSouth()
				? OrientationConstants.EAST_WEST
				: OrientationConstants.NORTH_SOUTH);
		relayoutMilestonePanel();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	protected void relayoutMilestonePanel() {
		if (getApp().isActionSelected(IQuestionaireActions.ACTION_HTML_PREVIEW)) {
			if (isNorthSouth()) {
				getFlipPnl().remove(getMilestonePnl());
				getFlipPnl().add(getMilestonePnl(), BorderLayout.WEST);
			} else {
				getFlipPnl().remove(getMilestonePnl());
				getFlipPnl().add(getMilestonePnl(), BorderLayout.NORTH);
			}
		} else {
			getFlipPnl().remove(getMilestonePnl());
			getFlipPnl().add(getMilestonePnl(), BorderLayout.NORTH);
		}

		revalidate();
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

		setLayout(new BorderLayout(0, 17));

		setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		add(getFlipPnl(), BorderLayout.CENTER);
		add(getButtonPnl(), BorderLayout.SOUTH);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				showPopupMenu(evt);
			}

			public void mousePressed(MouseEvent evt) {
				showPopupMenu(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				showPopupMenu(evt);
			}
		});

		getApp().selectAction(IQuestionaireActions.ACTION_HTML_PREVIEW);
	}

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setApp(Questionaire app) {
		fApp = app;
	}

	/**
	 * Set the orientation.
	 *
	 * @param	<code>config</code> the bundle.
	 */
	protected void setOrientation(OrientationConstants orientation) {
		fOrientation = orientation;
	}

	/**
	 * Show the popup menu if a <em>PopupTrigger</em> has been sent.
	 * 
	 * @param	<code>evt</code> the originating <code>MouseEvent</code>.
	 * @return	<code>true</code> if the popup menu is shown.
	 */
	protected boolean showPopupMenu(MouseEvent evt) {
		if (evt.isPopupTrigger()) {
			getPopupMenu().show((Component) evt.getSource(), evt.getX(), evt.getY());
			return true;
		}

		return false;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the current orientation.
	 * 
	 * @return	<code>true</code> if North-South-orientation;
	 * 			<code>false</code> if East-West-orientation.
	 */
	protected boolean isNorthSouth() {
		return (OrientationConstants.NORTH_SOUTH == fOrientation);
	}

	/**
	 * Answer the reference to the application.
	 *
	 * @return	the reference.
	 */
	protected Questionaire getApp() {
		return fApp;
	}

	/**
	 * Answer the document.
	 * 
	 * @return	the document.
	 */
	protected IQuestionaire getDocument() {
		return fDoc;
	}

	/**
	 * Answer the string resource with the given key.
	 * 
	 * @return    the resource.
	 */
	protected String getResource(String key) {
		return getApp().getConfig(key);
	}
}