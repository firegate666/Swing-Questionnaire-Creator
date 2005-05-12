package de.sep.dnd;

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
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;

import javax.swing.SwingUtilities;

import de.sdavids.dnd.*;
import de.sdavids.dnd.DropTargetAdapter;
import de.sep.ui.question.QuestionPanel;

/**
 * The <code>AnswerTypeDropPanel</code> is used to for drag & drop between answer
 * types.
 * 
 * If a <code>AnswerTypeTransferable</code> is dropped over a
 * <code>AnswerTypeDropPanel</code> a new answer type is inserted into the model.
 */
public class AnswerTypeDropPanel extends DropPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The question panel this answer type belongs to. */
	private QuestionPanel fQuestionPanel;

	//////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////////

	protected class AnswerTypeDropPanelDTListener extends DropTargetAdapter {

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
						AnswerTypeTranferable.ANSWERTYPE_FLAVOR));
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
					&& evt.isDataFlavorSupported(AnswerTypeTranferable.ANSWERTYPE_FLAVOR));
		}
		
		/**
		 * Invoked if the <code>DropTargetDropEvent</code> is acceptable.
		 *
		 * Adds the appropriate <code>IAnswerType</code> to the question
		 * model.
		 *
		 * @param	<code>evt</code> the event.
		 * @return	<code>true</code> if the model has been updated.
		 */
		public boolean execute(DropTargetDropEvent evt) {
			try {
				dropIt(getData(evt));

				return true;
			} catch (IOException e) {
				return false;
			} catch (UnsupportedFlavorException e) {
				return false;
			}
		}

		/**
		 * Highlight the panel if the user tries to drop something onto it.
		 *
		 * @param	<code>evt</code> the <code>DropTargetDragEvent</code>.
		 */
		public void dragEnter(DropTargetDragEvent evt) {
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

		/**
		 * Retrieve the string data form the <code>DropTargetDropEvent</code>.
		 *
		 * @param	<code>evt</code> the <code>DropTargetDropEvent</code>.
		 * @return	the data.
		 */
		protected String getData(DropTargetDropEvent evt)
			throws UnsupportedFlavorException, IOException {

			return (
				(String) evt.getTransferable().getTransferData(
					AnswerTypeTranferable.ANSWERTYPE_FLAVOR));
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////    

	/**
	 * Create a new <code>DropPanel</code> with the default &quot;highlight&quot;
	 * color orange.
	 * 
	 * @param	<code>questionPanel</code> the question panel this answer type
	 * 			panel belongs to.
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public AnswerTypeDropPanel(QuestionPanel questionPanel) {
		this(questionPanel, Color.orange);
	}

	/**
	 * Create a new <code>DropPanel</code> with the given &quot;highlight&quot;
	 * color.
	 * 
	 * @param	<code>questionPanel</code> the question panel this answer type
	 * 			panel belongs to.
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public AnswerTypeDropPanel(QuestionPanel questionPanel, Color highlight) {
		super(highlight);

		setQuestionPanel(questionPanel);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the question panel this <code>AnswerTypeDropPanel</code> belongs to.
	 * 
	 * @param	<code>panel</code> the panel.
	 */
	public void setQuestionPanel(QuestionPanel panel) {
		fQuestionPanel = panel;
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
		super.initialize();

		new DropTarget(
			this,
			DnDConstants.ACTION_COPY,
			new AnswerTypeDropPanelDTListener(),
			true);
	}

	/**
	 * Intiate the update of the model.
	 * 
	 * Unselects the panel and fires a <code>PropertyChange</code> event called
	 * <em>insertAnswerType</em>.
	 * 
	 * Use the event's <em>getOldValue()</em> to retrieve the index of this
	 * <code>AnswerTypeDropPanel</code> in it's parent. Use the event's
	 * <em>getNewValue()</em> to retrieve the dropped answer types code.
	 */
	protected void dropIt(String data) {
		showUnselected();

		firePropertyChange(
			"insertAnswerType",
			String.valueOf(SwingUtilities.getAccessibleIndexInParent(this)),
			data);
	}
}