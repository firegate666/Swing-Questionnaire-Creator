package de.sdavids.dnd;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;

import de.sdavids.swing.actions.*;

public class JDragButton extends JButton {

	private DragSourceListener fDSListener;

	protected JDragButtonDGLister fDGListener;

	public JDragButton(ActionImpl action) {
		this(action, DnDConstants.ACTION_COPY);
	}

	public JDragButton(ActionImpl action, int dragAction) {
		super(action);

		DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
			this,
			dragAction,
			getDragGestureListener());

		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	protected DragSourceListener getDragSourceListener() {
		if (null == fDSListener)
			fDSListener = new DragSourceAdapter() {};

		return fDSListener;
	}

	protected JDragButtonDGLister getDragGestureListener() {
		if (null == fDGListener) {
			fDGListener =
				new JDragButtonDGLister(
					getDragSourceListener(),
					getDNDAction());
		}

		return fDGListener;
	}
	
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		getAction().setEnabled(enabled);
	}
	
	protected ActionImpl getDNDAction() {
		return (ActionImpl) getAction();
	}
}