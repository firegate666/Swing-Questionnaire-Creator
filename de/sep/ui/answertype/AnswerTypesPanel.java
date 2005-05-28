package de.sep.ui.answertype;

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
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import sun.awt.VerticalBagLayout;
import de.sep.dnd.AnswerTypeDropPanel;
import de.sep.dnd.AnswerTypeEndDropPanel;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.ui.Questionaire;
import de.sep.ui.actions.IQuestionaireActions;
import de.sep.ui.question.QuestionPanel;

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

public class AnswerTypesPanel extends JPanel implements ContainerListener {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */
	/** 
	 * The panel which gets appended to the end, to insure that the user can
	 * drop something after the last <code>QuestionPanel</code>.
	 */
	private final AnswerTypeEndDropPanel END_DROP_PNL;

	/**
	 * To distinguish append/insert.
	 * 
	 * If we want to append <code>-1 == fIndex</code> otherwise <code>fIndex</code>
	 * is the index we want to insert the new <code>IQuestionPanel</code>.
	 * */
	private int fIndex;

	/**
	 * A reference to the <code>QuestionPanel</code> this
	 * <code>AnswerTypesPanel</code> is contained in.
	 */
	private QuestionPanel fQuestionPanel;

	/**  
	 * A reference to the application.
	 **/
	private Questionaire fApp;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create a panel showing the <code>AnswerType</code> model within the question model of parent.
	 *
	 * @param	<code>parent</code> the QuestionPanel where this is contained.
	 * @param	<code>app<code> a reference to the application.
	 */
	public AnswerTypesPanel(QuestionPanel qp, Questionaire app) {
		super();

		setApp(app);
		setQuestionPanel(qp);

		END_DROP_PNL = new AnswerTypeEndDropPanel(getQuestionPanel());

		END_DROP_PNL
			.addPropertyChangeListener("appendAnswerType", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				getApp().performAction(
				IQuestionaireActions.ACTION_APPEND_ANSWERTYPE, getContentPnl(), -1, evt.getNewValue().toString(), -1);
			}
		});

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * invoked if an AnswerTypePanel is added 
	 *
	 * @param	answerTypePanel <code>AnswerTypePanel</code> to be added
	 * @return the added component 
	 */

	public Component add(Component answerTypePanel) {
		remove(END_DROP_PNL);

		AnswerTypeDropPanel dp =
			new AnswerTypeDropPanel(getQuestionPanel(), getApp().getDNDSelectedColor());

		dp
			.addPropertyChangeListener("insertAnswerType", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				beInsert(Integer.valueOf((String) evt.getOldValue()).intValue());

				getApp().performAction(
					IQuestionaireActions.ACTION_INSERT_ANSWERTYPE,
					getContentPnl(),
					getContentPnl().hashCode(),
					evt.getNewValue().toString(),
					(int) (getIndex() * 0.5));
			}
		});

		if (isInsert()) {
			insertPanel((AbstractAnswerTypePanel) answerTypePanel, dp);
			super.add(END_DROP_PNL);
		} else {
			super.add(dp);
			super.add(answerTypePanel);
			super.add(END_DROP_PNL);
		}

		getQuestionPanel().beAnswerTypesNotCollapsed();

		return answerTypePanel;
	}

	/**
	 * invoked if an AnswerTypePanel is inserted 
	 *
	 * @param	answer <code>IAnswerType</code> to insert/append
	 * @param  index  position to insert into or -1 if append
	 */
	public void insertAnswerType(int index, IAnswerType answer)
		throws IllegalArgumentException, IllegalAnswerTypeException {

		getContentPnl().add(AnswerTypePanelFactory.create(answer, getApp()));
	}

	/**
	 * invoked if an AnswerTypePanel is appended 
	 *
	 * @param	answer <code>IAnswerType</code> to insert/append
	 * @param  index  position to insert into or -1 if append
	 */
	public void appendAnswerType(IAnswerType answer)
		throws IllegalAnswerTypeException {

		beAppend();

		getContentPnl().add(AnswerTypePanelFactory.create(answer, getApp()));
	}

	public void showEndDropPanelSelected() {
		END_DROP_PNL.showSelected();
	}

	public void showEndDropPanelUnselected() {
		END_DROP_PNL.showUnselected();
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
		setBackground(Color.white);
		setLayout(new VerticalBagLayout());
		addContainerListener(this);
		super.add(END_DROP_PNL);
	}

	/**
	 * invoke to enable appending of an answertype
	 */
	protected void beAppend() {
		fIndex = -1;
	}
	/**
	 * invoke to enable inserting of an answertype
	 */
	protected void beInsert(int index) throws IllegalArgumentException {
		if (0 > index)
			throw new IllegalArgumentException("" + index);

		fIndex = index;
	}
	/**
	 * Gets if the next add is insert or append
	 * @return is it an insert
	 */
	protected boolean isInsert() {
		return (-1 != getIndex());
	}
	/**
	 * gets the position to add answerType
	 * @return the position 
	 */
	protected int getIndex() {
		return fIndex;
	}

	/**
	 * Set the reference to the QuestionPanel.
	 *
	 * @param	the reference.
	 */
	protected void setQuestionPanel(QuestionPanel qp) {
		if (null == fQuestionPanel)
			fQuestionPanel = qp;

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
	 * invoked if an AnswerTypePanel is dropped 
	 *
	 * @param	panel <code>AnswerTypePanel</code> to be added
	 * @param  dp    <code>DropPanel</code> after inserted <code>AnswerTypePanel</code> 
	 */
	protected void insertPanel(
		AbstractAnswerTypePanel panel,
		AnswerTypeDropPanel dp) {

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
	 * Answer the root panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		return this;
	}

	/**
	 * Answer the Color when unselected.
	 * 
	 * @return	the Color 
	 */
	protected Color getUnselectedColor() {
		return Color.white;
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
	 * Answer the reference to the parent.
	 *
	 * @return	the reference.
	 */
	public QuestionPanel getQuestionPanel() {
		return fQuestionPanel;
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