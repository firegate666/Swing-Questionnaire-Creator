package de.sep.ui.questionaire;

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

import java.awt.Color;
import java.awt.Component;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sun.awt.VerticalBagLayout;

import de.sdavids.dnd.DropTargetAdapter;
import de.sep.dnd.QuestionDropPanel;
import de.sep.dnd.QuestionEndDropPanel;
import de.sep.dnd.QuestionTranferable;
import de.sep.model.question.IQuestion;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;
import de.sep.ui.actions.IQuestionaireActions;
import de.sep.ui.question.QuestionPanel;

/**
 * View for an <code>IQuestionaire</code>.
 */
public class QuestionairePanel extends JPanel implements ContainerListener {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The questionaire model. */
	private IQuestionaire fQuestionaire;

	/** A reference to the application. */
	private Questionaire fApp;

	/** 
	 * The panel which gets appended to the end, to insure that the user can
	 * drop something after the last <code>QuestionPanel</code>.
	 */
	private final QuestionEndDropPanel END_DROP_PNL;

	/**
	 * To distinguish append/insert.
	 * 
	 * If we want to append <code>-1 == fIndex</code> otherwise <code>fIndex</code>
	 * is the index we want to insert the new <code>IQuestionPanel</code>.
	 * */
	private int fIndex;

	/** List of all selected question panels. */
	private List fSelectedQuestionPanels;

	//////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////////

	protected class QuestionairePanelDTListener extends DropTargetAdapter {

		/**
		 * Create a new <code>DropTargetPanelDTListener</code>.
		 */
		public QuestionairePanelDTListener() {
			beAppend();
		}

		/**
		 * Answer whether the given <code>DropTargetDropEvent</code>
		 * is acceptable; it is acceptable if the dropped data is a
		 * question and if the drop action is
		 * <code>DnDConstants.ACTION_COPY</code>.
		 * 
		 * @param	<code>evt</code> the event.
		 * @return	<code>true</code> if it is acceptable.
		 */
		protected boolean isDragAcceptable(DropTargetDropEvent evt) {
			return (
				super.isDragAcceptable(evt)
					&& evt.getTransferable().isDataFlavorSupported(
						QuestionTranferable.QUESTION_FLAVOR));
		}

		/**
		 * Answer whether the given <code>DropTargetDragEvent</code>
		 * is acceptable; it is acceptable if the dropped data is a
		 * question and if the drop action is
		 * <code>DnDConstants.ACTION_COPY</code>.
		 * 
		 * @param	<code>evt</code> the event.
		 * @return	<code>true</code> if it is acceptable.
		 */
		protected boolean isDragAcceptable(DropTargetDragEvent evt) {
			return (
				super.isDragAcceptable(evt)
					&& evt.isDataFlavorSupported(QuestionTranferable.QUESTION_FLAVOR));
		}

		/**
		 * Invoked if the <code>DropTargetDropEvent</code> is acceptable.
		 * 
		 * Adds the appropriate <code>IQuestion</code> to the questionaire
		 * model.
		 * 
		 * @param	<code>evt</code> the event.
		 * @return	<code>true</code> if the model has been updated.
		 */
		public boolean execute(DropTargetDropEvent evt) {
			getApp().performAction(
				IQuestionaireActions.ACTION_APPEND_QUESTION,
				getContentPnl(),
				getContentPnl().hashCode(),
				"newQuestion");

			showUnselected();

			return true;
		}

		/**
		 * Highlight the panel if the user tries to drop something onto it.
		 * 
		 * @param	<code>evt</code> the <code>DropTargetDragEvent</code>.
		 */
		public void dragEnter(DropTargetDragEvent evt) {
			showAllQuestionPanelsInPreferredCollapsionState();

			if (isDragAcceptable(evt)) {
				super.dragEnter(evt);
				showSelected();
			}
		}

		/**
		 * Remove highlight from the panel.
		 * 
		 * @param	<code>evt</code> the <code>DropTargetEvent</code>.
		 */
		public void dragExit(DropTargetEvent evt) {
			super.dragExit(evt);

			showUnselected();
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	public QuestionairePanel(IQuestionaire questionaire, Questionaire app) {
		super();

		setApp(app);
		setQuestionaire(questionaire);

		END_DROP_PNL = new QuestionEndDropPanel(getApp().getDNDSelectedColor());

		END_DROP_PNL
			.addPropertyChangeListener("appendQuestion", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				getApp().performAction(
					IQuestionaireActions.ACTION_APPEND_QUESTION,
					evt.getSource(),
					evt.getSource().hashCode(),
					"newQuestion");
			}
		});

		initialize();
	}

	public Component add(Component questionPanel) {
		remove(END_DROP_PNL);

		QuestionDropPanel dp = new QuestionDropPanel(getApp().getDNDSelectedColor());

		dp.addPropertyChangeListener("insertQuestion", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				beInsert(((Integer) evt.getOldValue()).intValue());

				getApp().performAction(
					IQuestionaireActions.ACTION_INSERT_QUESTION,
					evt.getSource(),
					evt.getSource().hashCode(),
					"dropNewQuestion");

			}
		});

		if (isInsert()) {
			insertPanel((QuestionPanel) questionPanel, dp);
			super.add(END_DROP_PNL);
		} else {
			super.add(dp);
			super.add(questionPanel);
			super.add(END_DROP_PNL);
		}

		return questionPanel;
	}

	/**
	 * Initialize the panel.
	 * 
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		setBackground(Color.white);
		setLayout(new VerticalBagLayout());

		new DropTarget(
			this,
			DnDConstants.ACTION_COPY,
			new QuestionairePanelDTListener());

		addContainerListener(this);

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				if (!hasFocus()) {
					if (!isRequestFocusEnabled())
						setRequestFocusEnabled(true);

					requestFocus();
				}
			}
		});
	}

	/**
	 * Answer the questionaire model.
	 *
	 * @return	the question.
	 */
	public IQuestionaire getQuestionaire() {
		return fQuestionaire;
	}

	/**
	 * Set the questionaire's model.
	 *
	 * @param	<code>questionaire</code> the questionaire model.
	 * @throws	<code>IllegalArgumentException</code> if <code>questionaire</code>
	 *          is<code>null</code>.
	 */
	public void setQuestionaire(IQuestionaire questionaire)
		throws IllegalArgumentException {

		if (questionaire.isNull())
			throw new IllegalArgumentException("questionaire null");

		fQuestionaire = questionaire;

		fQuestionaire.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("questionsInsert".equals(evt.getPropertyName())) {
					QuestionPanel qp = new QuestionPanel((IQuestion) evt.getNewValue(), getApp());

					add(qp);

					qp.focusOnText();
				} else if ("questionsAdd".equals(evt.getPropertyName())) {
					QuestionPanel qp = new QuestionPanel((IQuestion) evt.getNewValue(), getApp());

					add(qp);

					reorderQuestions();

					qp.focusOnText();
				} else if ("questionsUpdated".equals(evt.getPropertyName())) {
					reorderQuestions();

					scrollToQuestion((IQuestion) evt.getNewValue());
				}
			}
		});
	}

	/**
	 * Reset all question panels to their preferred collapsion state.
	 */
	public void showAllQuestionPanelsInPreferredCollapsionState() {
		Component[] components = getComponents();

		int len = components.length;

		Component c;

		for (int i = 0; i < len; i++) {
			c = components[i];

			if (c instanceof QuestionPanel) {
				((QuestionPanel) c).showPreferredCollapsionState();
			}
		}
	}

	/**
	 * Collapse all question panels.
	 */
	public void showAllQuestionPanelsCollapsed() {
		Component[] components = getComponents();

		int len = components.length;

		Component c;

		for (int i = 0; i < len; i++) {
			c = components[i];

			if (c instanceof QuestionPanel) {
				((QuestionPanel) c).beAnswerTypesCollapsed();
			}
		}
	}

	/**
	 * Uncollapse all question panels.
	 */
	public void showAllQuestionPanelsNotCollapsed() {
		Component[] components = getComponents();

		int len = components.length;

		Component c;

		for (int i = 0; i < len; i++) {
			c = components[i];

			if (c instanceof QuestionPanel) {
				((QuestionPanel) c).beAnswerTypesNotCollapsed();
			}
		}
	}

	/**
	 * Reorder the question panels if the <em>AutoReorderQuestions</em>
	 * action is enabled, otherwise all highlights will be removed.
	 * 
	 * Does not preserve the focus.
	 */
	public void reorderQuestions() {
		if (getApp().isActionSelected(IQuestionaireActions.ACTION_AUTO_REORDER_QUESTIONS)) {
			updateQuestionPnlsAction();
		} else {
			removeHighlights();
		}

		if (!hasFocus()) {
			if (!isRequestFocusEnabled())
				setRequestFocusEnabled(true);

			requestFocus();
		}
	}

	/**
	 * Scroll to the given question.
	 * 
	 * Scrolls to the drop panel directly before the given question into view, or 
	 * to the enddrop panel if it is the last question.
	 * 
	 * @param	<code>question</code> the question.
	 * @throws	<code>NoSuchElementException</code> if the given question is not an
	 * 			element of this questionaire.
	 */
	public void scrollToQuestion(IQuestion question) {
		if (!getQuestionaire().contains(question))
			throw new NoSuchElementException(question.toString());

		int index =
			SwingUtilities.getAccessibleIndexInParent(getQuestionPanel(question));

		scrollRectToVisible(getComponent(index).getBounds());
		scrollRectToVisible(getComponent(index).getBounds());

		//set the focus on the question panel
		setNextFocusableComponent(getComponent(index));
		transferFocus();
	}

	/**
	 * Answer the question panel showing the given question.
	 * 
	 * @param	<code>question</code> the question.
	 * @throws	<code>NoSuchElementException</code> if the given question is not an
	 * 			element of this questionaire.
	 */
	protected QuestionPanel getQuestionPanel(IQuestion question) {
		List components = new ArrayList(Arrays.asList(getComponents()));

		Iterator it = components.iterator();

		QuestionPanel qp;
		Object o;

		while (it.hasNext()) {
			o = it.next();
			;
			if (o instanceof QuestionPanel) {
				qp = (QuestionPanel) o;

				if (question.equals(qp.getQuestion()))
					return qp;
			}
		}

		throw new NoSuchElementException(question.toString());
	}

	/**
	 * Highlight the questionaire panel.
	 */
	protected void showSelected() {
		Component[] components = getContentPnl().getComponents();

		int len = components.length;

		if (0 == len) {
			setBackground(getApp().getDNDSelectedColor());
		} else {
			//select the EndDropPanel -> selects us as well
			 ((QuestionEndDropPanel) components[len - 1]).showSelected();
		}
	}

	/**
	 * Remove the highlight questionaire panel.
	 */
	protected void showUnselected() {
		Component[] components = getContentPnl().getComponents();

		int len = components.length;

		if (0 == len) {
			setBackground(getUnselectedColor());
		} else {
			((QuestionEndDropPanel) components[len - 1]).showUnselected();
			setBackground(getUnselectedColor());
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setApp(Questionaire app) {
		fApp = app;
	}

	protected void removeHighlights() {
		Component[] components = getComponents();
		Component c;

		for (int i = (components.length - 1); i >= 0; i--) {
			c = components[i];

			if (c instanceof QuestionPanel) {
				((QuestionPanel) c).showUnselected();
			}
		}
	}

	protected void updateQuestionPnlsAction() {
		remove(END_DROP_PNL);

		Component[] components = getComponents();

		int len = components.length;

		TreeMap qPnls = new TreeMap();
		ArrayList dropPnls = new ArrayList();

		QuestionPanel qp;
		Component c;

		for (int i = 0; i < len; i++) {
			c = components[i];

			if (c instanceof QuestionPanel) {
				qp = (QuestionPanel) c;
				qp.showUnselected();
				qPnls.put(new Integer(qp.getQuestion().getNumber()), qp);
			} else if (c instanceof QuestionDropPanel) {
				dropPnls.add(c);
			}
		}

		removeAll();

		Iterator it = qPnls.keySet().iterator();

		int max = dropPnls.size() - 1;

		while (it.hasNext()) {
			super.add((QuestionDropPanel) dropPnls.remove(max));
			max--;
			qp = (QuestionPanel) qPnls.get((Integer) it.next());
			super.add(qp);
		}

		super.add(END_DROP_PNL);

		revalidate();
		repaint();
	}

	protected void beAppend() {
		fIndex = -1;
	}

	protected void beInsert(int index) {
		fIndex = index;
	}

	protected boolean isInsert() {
		return (-1 != getIndex());
	}

	protected int getIndex() {
		return fIndex;
	}

	protected void insertPanel(QuestionPanel panel, QuestionDropPanel dp) {
		List components = new ArrayList(Arrays.asList(getComponents()));

		components.add(getIndex(), panel);
		components.add(getIndex(), dp);

		removeAll();

		Iterator it = components.iterator();

		while (it.hasNext()) {
			super.add((Component) it.next());
		}
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
	 * @return    the resource.
	 */
	protected String getResource(String key) {
		return getApp().getConfig(key);
	}

	/**
	 * Answer the root panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		return this;
	}

	protected Color getUnselectedColor() {
		return Color.white;
	}

	/////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT CONTAINERLISTNER
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Revalidate when a component has been added to the container.
	 *
	 * @param	<code>evt</code> the event.
	 */
	public void componentAdded(ContainerEvent evt) {
		revalidate();
	}

	/**
	 * Revalidate when a component has been removed from the container.
	 *
	 * @param	<code>evt</code> the event.
	 */
	public void componentRemoved(ContainerEvent evt) {
		revalidate();
	}
}