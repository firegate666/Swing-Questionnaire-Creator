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

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.InvalidDnDOperationException;

import de.sdavids.dnd.JDragButton;
import de.sdavids.dnd.JDragButtonDGLister;
import de.sdavids.swing.actions.ActionImpl;

public class DraggableQuestion extends JDragButton {

	protected class DraggableQuestionDGLister extends JDragButtonDGLister {
		public DraggableQuestionDGLister(
			DragSourceListener listener,
			ActionImpl action) {
			super(listener, action);
		}

		public void dragGestureRecognized(DragGestureEvent evt)
			throws InvalidDnDOperationException {

			if (getAction().isEnabled()) {
				evt.startDrag(
					DragSource.DefaultCopyDrop,
					new QuestionTranferable(),
					getListener());
			}
		}
	}

	public DraggableQuestion(ActionImpl action) {
		super(action, DnDConstants.ACTION_COPY);
		setText("");
	}

	protected JDragButtonDGLister getDragGestureListener() {
		if (null == fDGListener) {
			fDGListener =
				new DraggableQuestionDGLister(getDragSourceListener(), getDNDAction());
		}

		return fDGListener;
	}
}