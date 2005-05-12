package de.sep.ui.html;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.sep.logic.web.html.QuestionaireHTMLFactory;

import de.sep.model.questionaire.IQuestionaire;

/**
 * HTML preview of an <code>IQuestionaire</code>.
 */
public class HTMLPreviewPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The questionaire model. */
	private IQuestionaire fQuestionaire;

	/** The factory used to created the HTML. */
	private QuestionaireHTMLFactory fHTMLFactory;

	/** Whether the HTML should be regenerated or not on <code>setVisible</code>. */
	private boolean fDirty;

	/* ----------- Controls ---------- */

	/** Used to display the HTML. */
	private JEditorPane fHTMLView;

	/** Scrolling support. */
	private JScrollPane fContentPanel;

	/* ----------- Actions ---------- */

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create a HTML preview panel showing the questionaire model.
	 *
	 * @param	<code>questionaire</code> the questionaire model.
	 * @param	<code>app<code> a HTML factory.
	 */
	public HTMLPreviewPanel(
		IQuestionaire questionaire,
		QuestionaireHTMLFactory factory) {

		super();

		setFactory(factory);
		setDocument(questionaire);

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// CONTROLS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the HTML <code>JEditorPane</code>.
	 *
	 * @return	the panel.
	 */
	protected JEditorPane getHTMLView() {
		if (null == fHTMLView) {
			fHTMLView = new JEditorPane("text/html", getHTML()); //Non-I18N-NLS

			fHTMLView.setEditable(false);
			fHTMLView.setAutoscrolls(true);

			fHTMLView.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					processEvent(evt);
					dispatchEvent(evt);
				}

				public void mousePressed(MouseEvent evt) {
					processEvent(evt);
					dispatchEvent(evt);
				}

				public void mouseReleased(MouseEvent evt) {
					processEvent(evt);
					dispatchEvent(evt);
				}
			});
		}

		return fHTMLView;
	}

	/**
	 * Answer content panel containing the HTML view.
	 *
	 * @return	the panel.
	 */
	protected JScrollPane getContentPanel() {
		if (null == fContentPanel) {
			fContentPanel = new JScrollPane(getHTMLView());
		}

		return fContentPanel;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the questionaire model.
	 *
	 * @param	<code>questionaire</code> the questionaire model.
	 * @throws	<code>IllegalArgumentException</code> if <code>questionaire</code>
	 *          is<code>null</code>.
	 */
	public void setDocument(IQuestionaire questionaire)
		throws IllegalArgumentException {

		if (null == questionaire)
			throw new IllegalArgumentException("questionaire null"); //Non-I18N-NLS

		fQuestionaire = questionaire;

		fQuestionaire.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				beDirty();
			}
		});

		updateHTML();
	}

	/**
	 * Makes the component visible or invisible.
	 *
	 * Updates the HTML view if <code>isDirty()</code>
	 *
	 * @param	visible  <code>true</code> to make the component visible.
	 * @see	#isDirty.
	 */
	public void setVisible(boolean visible) {
		if (visible) {

			if (isDirty()) {
				updateHTML();
			}

			if (!getHTMLView().hasFocus()) {
				if (!getHTMLView().isRequestFocusEnabled())
					getHTMLView().setRequestFocusEnabled(true);

				getHTMLView().requestFocus();
			}
		}

		super.setVisible(visible);
	}

	/**
	 * Update the HTML view.
	 */
	protected synchronized void updateHTML() {
		getHTMLView().setText(getHTML());
		beNotDirty();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the panel.
	 *
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		setLayout(new BorderLayout());

		add(getContentPanel(), BorderLayout.CENTER);
	}

	/**
	 * Set the HTML panel to the &quot;dirty&quot; state, i.e. the next time
	 * <code>setVisible()</code> is called the HTML will be updated.
	 * @see	#beNotDirty
	 */
	protected void beDirty() {
		fDirty = true;
	}

	/**
	 * Set the HTML panel to the &quot;not dirty&quot; state,
	 *
	 * @see	#beDirty
	 */
	protected void beNotDirty() {
		fDirty = false;
	}

	/**
	 * Set the HTML factory used to genererate the HTML from the model,
	 *
	 * @param	<code>factory</code> the factory.
	 */
	protected void setFactory(QuestionaireHTMLFactory factory) {
		fHTMLFactory = factory;
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer whether the HTML representation should be regenerated or not.
	 *
	 * @see	<code>true</code> if it has to be regenerated.
	 */
	protected boolean isDirty() {
		return fDirty;
	}

	/**
	 * Answer the questionaire model.
	 *
	 * @return	the question.
	 */
	protected IQuestionaire getQuestionaire() {
		return fQuestionaire;
	}

	/**
	 * Answer the HTML representation of the model,
	 *
	 * @return	the HTML representation.
	 */
	protected String getHTML() {
        String HTMLCode = fHTMLFactory.create(getQuestionaire());
//        System.out.println(HTMLCode);
		return HTMLCode;
	}
}