package de.sep.ui;

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

import javax.swing.JToolBar;

import de.sdavids.dnd.JDragButton;
import de.sdavids.dnd.JDragLabel;
import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.actions.ActionImpl;
import de.sep.dnd.DraggableAnswerType;
import de.sep.dnd.DraggableQuestion;
import de.sep.model.answertype.AnswerType;
import de.sep.ui.actions.DropNewAnswerTypeAction;
import de.sep.ui.actions.IQuestionaireActions;

public class MainPalette extends JToolBar {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** A reference to the application. */
	private Questionaire fApp;

	/* ----------- Controls ---------- */

	/** Draggable button for an <code>IQuestion</code>. */
	private JDragButton fQuestionBtn;

	/** Draggable label for an <code>AnswerType.COMPLETION_OPEN</code>. */
	private JDragLabel fCompletionOpenLbl;

	/** Draggable label for an <code>AnswerType.COMPLETION_LIMITED</code>. */
	private JDragLabel fCompletionLimitedLbl;

	/** Draggable label for an <code>AnswerType.MATCH_MULTIPLE</code>. */
	private JDragLabel fMatchMultipleLbl;

	/** Draggable label for an <code>AnswerType.MATCH_SINGLE</code>. */
	private JDragLabel fMatchSingleLbl;

	/** Draggable label for an <code>AnswerType.OPEN_QUALIFIED</code>. */
	private JDragLabel fOpenQualifiedLbl;

	/** Draggable label for an <code>AnswerType.OPEN_QUANTIFIED</code>. */
	private JDragLabel fOpenQuantifiedLbl;

	/** Draggable label for an <code>AnswerType.OPTION_BINARY</code>. */
	private JDragLabel fOptionBinaryLbl;

	/** Draggable label for an <code>AnswerType.OPTION_LIMITED</code>. */
	private JDragLabel fOptionLimitedLbl;

	/** Draggable label for an <code>AnswerType.OPTION_VARIABLE</code>. */
	private JDragLabel fOptionVariableLbl;

	/** Draggable label for an <code>AnswerType.ORDER</code>. */
	private JDragLabel fOrderLbl;

	/** Draggable label for an <code>AnswerType.SCALE_CALCULATED</code>. */
	private JDragLabel fScaleCalculatedLbl;

	/** Draggable label for an <code>AnswerType.SCALE_LABLED</code>. */
	private JDragLabel fScaleLabledLbl;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create a new toolbar.  Orientration defaults to horizontal.
	 * 
	 * @param	<code>app<code> a reference to the application.
	 */
	public MainPalette(Questionaire app) {
		super();

		setApp(app);

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// CONTROLS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the draggable button for an <code>IQuestion</code>.
	 * 
	 * @return  the buttonn.
	 * @see		de.sep.model.question.IQuestion.
	 */
	public JDragButton getQuestionButton() {
		if (null == fQuestionBtn)
			fQuestionBtn = new DraggableQuestion(getApp().actionForName(IQuestionaireActions.ACTION_APPEND_QUESTION));

		return fQuestionBtn;
	}

	/**
	 * Answer the draggable label for an
	 * <code>AnswerType.COMPLETION_OPEN</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#COMPLETION_OPEN.
	 */
	public JDragLabel getCompletionOpenLabel() {
		if (null == fCompletionOpenLbl) {
			fCompletionOpenLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newCompletionOpen", getApp()), AnswerType.COMPLETION_OPEN);
		}

		return fCompletionOpenLbl;
	}

	/**
	 * Answer the draggable label for an 
	 * <code>AnswerType.COMPLETION_LIMITED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#COMPLETION_LIMITED.
	 */
	public JDragLabel getCompletionLimitedLabel() {
		if (null == fCompletionLimitedLbl) {
			fCompletionLimitedLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newCompletionLimited", getApp()), AnswerType.COMPLETION_LIMITED);
		}

		return fCompletionLimitedLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.MATCH_MULTIPLE</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#MATCH_MULTIPLE.
	 */
	public JDragLabel getMatchMultipleLabel() {
		if (null == fMatchMultipleLbl) {
			fMatchMultipleLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newMatchMultiple", getApp()), AnswerType.MATCH_MULTIPLE);
		}

		return fMatchMultipleLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.MATCH_SINGLE</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#MATCH_SINGLE.
	 */
	public JDragLabel getMatchSingleLabel() {
		if (null == fMatchSingleLbl) {
			fMatchSingleLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newMatchSingle", getApp()), AnswerType.MATCH_SINGLE);
		}

		return fMatchSingleLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.OPEN_QUALIFIED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#OPEN_QUALIFIED.
	 */
	public JDragLabel getOpenQualifiedLabel() {
		if (null == fOpenQualifiedLbl) {
			fOpenQualifiedLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOpenQualified", getApp()), AnswerType.OPEN_QUALIFIED);
		}

		return fOpenQualifiedLbl;
	}

	/**
	 * Answer the draggable label for an
	 * <code>AnswerType.OPEN_QUANTIFIED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#OPEN_QUANTIFIED.
	 */
	public JDragLabel getOpenQuantifiedLabel() {
		if (null == fOpenQuantifiedLbl) {
			fOpenQuantifiedLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOpenQuantified", getApp()), AnswerType.OPEN_QUANTIFIED);
		}

		return fOpenQuantifiedLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.OPTION_BINARY</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#OPTION_BINARY.
	 */
	public JDragLabel getOptionBinaryLabel() {
		if (null == fOptionBinaryLbl) {
			fOptionBinaryLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOptionBinary", getApp()), AnswerType.OPTION_BINARY);
		}

		return fOptionBinaryLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.OPTION_LIMITED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#OPTION_LIMITED.
	 */
	public JDragLabel getOptionLimitedLabel() {
		if (null == fOptionLimitedLbl) {
			fOptionLimitedLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOptionLimited", getApp()), AnswerType.OPTION_LIMITED);
		}

		return fOptionLimitedLbl;
	}

	/**
	 * Answer the draggable label for an
	 * <code>AnswerType.OPTION_VARIABLE</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#OPTION_VARIABLE.
	 */
	public JDragLabel getOptionVariableLabel() {
		if (null == fOptionVariableLbl) {
			fOptionVariableLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOptionVariable", getApp()), AnswerType.OPTION_VARIABLE);
		}

		return fOptionVariableLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.ORDER</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#ORDER.
	 */
	public JDragLabel getOrderLabel() {
		if (null == fOrderLbl) {
			fOrderLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newOrder", getApp()), AnswerType.ORDER);
		}

		return fOrderLbl;
	}

	/**
	 * Answer the draggable label for an
	 * <code>AnswerType.SCALE_CALCULATED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#SCALE_CALCULATED.
	 */
	public JDragLabel getScaleCalculatedLabel() {
		if (null == fScaleCalculatedLbl) {
			fScaleCalculatedLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newScaleCalculated", getApp()), AnswerType.SCALE_CALCULATED);
		}

		return fScaleCalculatedLbl;
	}

	/**
	 * Answer the draggable label for an <code>AnswerType.SCALE_LABLED</code>.
	 * 
	 * @return  the label.
	 * @see		de.sep.model.answertype.AnswerType#SCALE_LABLED.
	 */
	public JDragLabel getScaleLabledLabel() {
		if (null == fScaleLabledLbl) {
			fScaleLabledLbl =
				new DraggableAnswerType(new DropNewAnswerTypeAction("act.newScaleLabled", getApp()), AnswerType.SCALE_LABLED);
		}

		return fScaleLabledLbl;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Whether the answer type labels should be enabled or not.
	 * 
	 * @param	<code>enable</code> enabled if <code>true</code>.
	 */
	public void toggleAnswerTypeJDragLabels(boolean enable) {
		getCompletionLimitedLabel().setEnabled(enable);
		getCompletionOpenLabel().setEnabled(enable);
		getMatchMultipleLabel().setEnabled(enable);
		getMatchSingleLabel().setEnabled(enable);
		getOpenQualifiedLabel().setEnabled(enable);
		getOpenQuantifiedLabel().setEnabled(enable);
		getOptionBinaryLabel().setEnabled(enable);
		getOptionLimitedLabel().setEnabled(enable);
		getOptionVariableLabel().setEnabled(enable);
		getOrderLabel().setEnabled(enable);
		getScaleCalculatedLabel().setEnabled(enable);
		getScaleLabledLabel().setEnabled(enable);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the main palette.
	 */
	protected void initialize() {
		add(getQuestionButton());

		addSeparator();
		addSeparator();

		add(getOpenQualifiedLabel());
	
		addSeparator();

		add(getOpenQuantifiedLabel());

		addSeparator();

		add(getCompletionOpenLabel());
//		add(getCompletionLimitedLabel());

//		addSeparator();
//
//		add(getMatchSingleLabel());
//		add(getMatchMultipleLabel());

		addSeparator();
		
		add(getOptionBinaryLabel());
		
		addSeparator();
		
		add(getOptionLimitedLabel());
//		add(getOptionVariableLabel());

		addSeparator();
//		
		add(getScaleCalculatedLabel());
//		add(getScaleLabledLabel());

		addSeparator();

		add(getOrderLabel());

		setName(getApp().getConfig("tb.main"));
		setFloatable(
			Boolean.valueOf(getApp().getConfig("tb.main.floating")).booleanValue());
		setVisible(false);
	}

	/**
	 * Set the reference to the application.
	 *
	 * @param	the reference.
	 */
	protected void setApp(Questionaire app) {
		fApp = app;
	}

	/* ----------- Accessors --------------------- */

	/**
	 * Answer the reference to the application.
	 *
	 * @return	the reference.
	 */
	protected Questionaire getApp() {
		return fApp;
	}
}