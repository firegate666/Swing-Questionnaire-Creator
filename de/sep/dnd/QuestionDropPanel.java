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
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;

import javax.swing.SwingUtilities;

import de.sdavids.dnd.DropPanel;
import de.sdavids.dnd.DropTargetAdapter;
import de.sep.ui.questionaire.QuestionairePanel;

/**
 * The <code>QuestionDropPanel</code> is used to for drag & drop between questions.
 * 
 * If a <code>QuestionTransferable</code> is dropped over a
 * <code>QuestionDropPanel</code> a new question is inserted into the model.
 */
public class QuestionDropPanel extends DropPanel {

	//////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	//////////////////////////////////////////////////////////////////////////////

	protected class QuestionDropPanelDTListener extends DropTargetAdapter {

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
		 * @param	<code>evt</code> the event.
		 * @return	<code>true</code>.
		 */
		public boolean execute(DropTargetDropEvent evt) {
			dropIt();

			return true;
		}

		/**
		 * Highlight the panel if the user tries to drop an acceptable
		 * transferable onto it.
		 * 
		 * @param	<code>evt</code> the <code>DropTargetDragEvent</code>.
		 * @see	#isDragAcceptable.
		 */
		public void dragEnter(DropTargetDragEvent evt) {
			if (isDragAcceptable(evt)) {
				showSelected();
			} else {
				//for this to work the drop panels have to be added directly
				//to the QuestionairePanel
				((QuestionairePanel) getParent())
					.showAllQuestionPanelsInPreferredCollapsionState();

				evt.rejectDrag();
			}
		}

		/**
		 * Remove highlight from the panel.
		 * 
		 * @param	<code>evt</code> the <code>DropTargetEvent</code>.
		 */
		public void dragExit(DropTargetEvent evt) {
			showUnselected();
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////    

	/**
	 * Create a new <code>QuestionDropPanel</code> with the default
	 * &quot;highlight&quot; color orange.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public QuestionDropPanel() {
		this(Color.orange);
	}

	/**
	 * Create a new <code>QuestionDropPanel</code> with the given
	 * &quot;highlight&quot; color.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public QuestionDropPanel(Color highlight) {
		super(highlight);
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
			new QuestionDropPanelDTListener(),
			true);
	}

	/**
	 * Intiate the update of the model.
	 * 
	 * Unselects the panel and fires a <code>PropertyChange</code> event called
	 * <em>insertQuestion</em>.
	 * 
	 * Use the event's <em>getOldValue()</em> to retrieve the index of this
	 * <code>QuestionDropPanel</code> in it's parent.
	 */
	protected void dropIt() {
		showUnselected();

		firePropertyChange(
			"insertQuestion",
			SwingUtilities.getAccessibleIndexInParent(this),
			-1);
	}
}