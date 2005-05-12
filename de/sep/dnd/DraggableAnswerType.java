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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import de.sdavids.dnd.JDragLabel;
import de.sdavids.dnd.JDragLabelDGLister;
import de.sdavids.swing.actions.ActionImpl;
import de.sep.model.answertype.AnswerType;

public class DraggableAnswerType extends JDragLabel {
	private final AnswerType TYPE;

	private final Border RAISED_BORDER;
	private final Border NORMAL_BORDER;

	protected class DraggableAnswerTypeDGLister extends JDragLabelDGLister {
		public DraggableAnswerTypeDGLister(
			DragSourceListener listener,
			ActionImpl action) {

			super(listener, action);
		}

		public void dragGestureRecognized(DragGestureEvent evt)
			throws InvalidDnDOperationException {

			if (getAction().isEnabled()) {
				evt.startDrag(
					DragSource.DefaultCopyDrop,
					new AnswerTypeTranferable(String.valueOf(TYPE.getValue())),
					getListener());
			}
		}
	}

	public DraggableAnswerType(ActionImpl action, AnswerType type) {
		super(action, DnDConstants.ACTION_COPY);
		setText("");

		TYPE = type;

		RAISED_BORDER = BorderFactory.createEtchedBorder();
		NORMAL_BORDER = BorderFactory.createEmptyBorder(2, 2, 2, 2);

		setBorder(NORMAL_BORDER);

		addMouseListener(new MouseAdapter() {
   		public void mousePressed(MouseEvent e) {
   			if (isEnabled() && (!hasFocus())) {
					if (!isRequestFocusEnabled())
						setRequestFocusEnabled(true);

					requestFocus();
				}					
   			}
			
			public void mouseEntered(MouseEvent evt) {
				if (isEnabled())
					setBorder(RAISED_BORDER);
			}

			public void mouseExited(MouseEvent evt) {
				if (isEnabled())
					setBorder(NORMAL_BORDER);
			}
		});
	}

	protected JDragLabelDGLister getDragGestureListener() {
		if (null == fDGListener)
			fDGListener =
				new DraggableAnswerTypeDGLister(getDragSourceListener(), getDNDAction());

		return fDGListener;
	}
}