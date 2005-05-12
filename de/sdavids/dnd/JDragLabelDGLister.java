package de.sdavids.dnd;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.InvalidDnDOperationException;

import de.sdavids.swing.actions.ActionImpl;

public class JDragLabelDGLister implements DragGestureListener {

	private ActionImpl fAction;
	private DragSourceListener fListener;

	public JDragLabelDGLister(
		DragSourceListener listener,
		ActionImpl action) {
		fListener = listener;
		fAction = action;
	}

	protected ActionImpl getAction() {
		return fAction;
	}

	protected DragSourceListener getListener() {
		return fListener;
	}

	public void dragGestureRecognized(DragGestureEvent evt)
		throws InvalidDnDOperationException {

		if (!getAction().isEnabled())
			return;
	}
}