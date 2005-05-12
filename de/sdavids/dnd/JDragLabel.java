package de.sdavids.dnd;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JLabel;

import de.sdavids.swing.actions.ActionImpl;


public class JDragLabel
			extends JLabel
			implements Serializable {

    private DragSourceListener fDSListener;

	protected JDragLabelDGLister fDGListener;
	
	private ActionImpl fDNDAction;
	
	public JDragLabel(ActionImpl action) {
		this(action, DnDConstants.ACTION_COPY);
	}

	public JDragLabel(ActionImpl action, int dragAction) {
		super();

		setAction(action);

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
	
	protected JDragLabelDGLister getDragGestureListener() {
		if (null == fDGListener) {
			fDGListener =
				new JDragLabelDGLister(
					getDragSourceListener(),
					getDNDAction());
		}

		return fDGListener;
	}	
	
	protected void setAction(ActionImpl action) {
		fDNDAction = action;
		
		setText(fDNDAction.getName());
		setEnabled(fDNDAction.isEnabled());
		setIcon(fDNDAction.getIcon());
		setToolTipText(fDNDAction.getShortDescription());
	}
	
	protected ActionImpl getDNDAction() {
		return fDNDAction;
	}
	
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		getDNDAction().setEnabled(enabled);
	}		
}
